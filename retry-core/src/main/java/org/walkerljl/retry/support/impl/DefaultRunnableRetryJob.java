package org.walkerljl.retry.support.impl;

import java.util.Date;
import java.util.List;

import org.walkerljl.retry.RetryHandler;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.exception.RetryException;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.log.util.LoggerUtil;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryLogStatusEnum;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.ReleaseRetryJobParam;
import org.walkerljl.retry.support.RetryContext;
import org.walkerljl.retry.support.RetryJobHandlerRepository;
import org.walkerljl.retry.support.RunnableRetryJob;
import org.walkerljl.retry.util.AssertUtil;
import org.walkerljl.retry.util.NumUtil;
import org.walkerljl.retry.util.RetryIntervalCalculator;
import org.walkerljl.retry.util.RetryUtil;

/**
 *
 * @author xingxun
 */
public class DefaultRunnableRetryJob implements RunnableRetryJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRunnableRetryJob.class);

    private RetryContext retryContext;
    private RetryJob     retryJob;
    private RetryService retryService;

    private String retryJobIdentifier;

    public DefaultRunnableRetryJob(RetryContext retryContext,
                                   RetryJob retryJob, RetryService retryService) {
        this.retryContext = retryContext;
        this.retryJob = retryJob;
        this.retryService = retryService;
        this.retryJobIdentifier = RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId());
    }

    public void run() {
        try {
            //锁定重试任务
            boolean isLocked = lockRetryJob(retryJob);
            if (!isLocked) {
                if (LOGGER.isInfoEnabled()) {
                    LoggerUtil.info(LOGGER, String.format("[%s]Fail to lock the retry job.",
                            retryJobIdentifier));
                }
                return;
            }
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to lock the retry job.",
                    retryJobIdentifier));
        }

        boolean isSuccess = false;
        Throwable errorThroable = null;
        try {
            doOnRunningInterceptors(retryContext, retryJob);

            //获取最新重试任务信息
            retryJob = retryService.getRetryJob(retryJob.getBizType(), retryJob.getBizId());
            AssertUtil.assertTrue(retryJob != null, String.format("[%s]Fail to get newest retry job.", retryJobIdentifier));

            //执行重试业务
            RetryHandler retryTarget = RetryJobHandlerRepository.lookup(retryJob.getTargetIdentifer());
            if (retryTarget == null) {
                throw new RetryException(String.format("[%s]No retry handler.", retryJobIdentifier));
            }
            retryContext.setAttribute(RetryContext.BUSINESS_PARAMS, retryJob.getParams());
            retryTarget.retry(retryContext);
            isSuccess = true;
            if (LOGGER.isInfoEnabled()) {
                LoggerUtil.info(LOGGER, String.format("[]Success to execute one retry job.",
                        retryJobIdentifier));
            }

            doOnCompletedInterceptors(retryContext, retryJob);
        } catch (Exception e) {
            doOnErrorInterceptors(retryContext, retryJob);
            errorThroable = e;
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to execute one retry job.",
                    retryJobIdentifier));
        } finally {
            unlockRetryJobAndRecoradLog(retryJob, isSuccess, errorThroable);
            if (isAbort(retryJob)) {
                doOnAbortInterceptors(retryContext, retryJob);
            }
        }
    }

    private RetryConfig getRetryConfig() {
        return (RetryConfig) retryContext.getAttribute(RetryContext.RETRY_CONFIG);
    }

    private boolean isAbort(RetryJob retryJob) {
        return (NumUtil.intValue(retryJob.getAttempts()) + 1) >= NumUtil.intValue(retryJob.getMaxAttempts());
    }

    private List<RetryListener> getListeners() {
        return getRetryConfig().getListeners();
    }

    private void doOnRunningInterceptors(RetryContext retryContext, RetryJob retryJob) {
        for (RetryListener listener : getListeners()) {
            listener.onRunning(retryContext, retryJob);
        }
    }

    private void doOnCompletedInterceptors(RetryContext retryContext, RetryJob retryJob) {
        for (RetryListener listener : getListeners()) {
            listener.onCompleted(retryContext, retryJob);
        }
    }

    private void doOnErrorInterceptors(RetryContext retryContext, RetryJob retryJob) {
        for (RetryListener listener : getListeners()) {
            listener.onError(retryContext, retryJob);
        }
    }

    private void doOnAbortInterceptors(RetryContext retryContext, RetryJob retryJob) {
        for (RetryListener listener : getListeners()) {
            listener.onAbort(retryContext, retryJob);
        }
    }

    private void unlockRetryJobAndRecoradLog(RetryJob retryJob, boolean isSuccess, Throwable errorThroable) {

        //释放重试任务
        try {
            unlockRetryJob(retryJob, isSuccess);
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to unlock the retry job.",
                    retryJobIdentifier));
        }

        //记录重试日志
        try {
            buildAndRecordRetryLog(retryJob, isSuccess, errorThroable);
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to record execute log the retry job.",
                    retryJobIdentifier));
        }
    }

    private void unlockRetryJob(RetryJob retryJob, boolean isSuccess) {
        ReleaseRetryJobParam releaseRetryJobParam = buildUnlockRetryJobParam

                (retryJob,
                        isSuccess);
        retryService.unlockRetryJob(releaseRetryJobParam);
    }

    private ReleaseRetryJobParam buildUnlockRetryJobParam(RetryJob retryJob, boolean isSuccess) {
        ReleaseRetryJobParam releaseRetryJobParam = new ReleaseRetryJobParam();

        releaseRetryJobParam.setRetryJobId(retryJob.getId());
        releaseRetryJobParam.setAttempts(retryJob.getAttempts() + 1);
        releaseRetryJobParam.setStatus((isSuccess ? RetryJobStatusEnum.PROCESSED
                : RetryJobStatusEnum.FAILURE));
        Date nextRetryTime = RetryIntervalCalculator.getNextRetryTime(retryJob.getLastRetryTime(),
                retryJob.getRetryRule(), releaseRetryJobParam.getAttempts() + 1);
        releaseRetryJobParam.setNextRetryTime(nextRetryTime);
        releaseRetryJobParam.setModified(new Date());
        return releaseRetryJobParam;
    }

    private boolean lockRetryJob(RetryJob retryJob) {
        LockRetryJobParam lockRetryJobParam = buildLockRetryJobParam(retryJob);
        int effectedRecords = retryService.lockRetryJob(lockRetryJobParam);
        return effectedRecords > 0;
    }

    private LockRetryJobParam buildLockRetryJobParam(RetryJob retryJob) {
        LockRetryJobParam lockRetryJobParam = new LockRetryJobParam();
        Date currentTime = new Date();
        lockRetryJobParam.setRetryJobId(retryJob.getId());
        lockRetryJobParam.setLastRetryTime(currentTime);
        lockRetryJobParam.setModified(currentTime);
        return lockRetryJobParam;
    }

    private void buildAndRecordRetryLog(RetryJob retryJob, boolean isSuccess, Throwable e) {
        String errorMsg = (e == null ? "" : e.getMessage());
        RetryLog retryLog = (isSuccess ? buildSuccessRetryLog(retryJob)
                : buildFailureRetryLog(retryJob, errorMsg));
        retryService.recordRetryLog(retryLog);
    }

    private RetryLog buildBaseRetryLog(RetryJob retryJob) {
        RetryLog retryLog = new RetryLog();
        retryLog.setRetryJobId(retryJob.getId());
        retryLog.setAttempts(retryJob.getAttempts());
        retryLog.setCreator("");
        retryLog.setCreated(new Date());
        return retryLog;
    }

    private RetryLog buildFailureRetryLog(RetryJob retryJob, String errorMsg) {
        RetryLog retryLog = buildBaseRetryLog(retryJob);
        retryLog.setStatus(RetryLogStatusEnum.FAILURE);
        retryLog.setDescription(errorMsg == null ? "" : errorMsg);
        return retryLog;
    }

    private RetryLog buildSuccessRetryLog(RetryJob retryJob) {
        RetryLog retryLog = buildBaseRetryLog(retryJob);
        retryLog.setStatus(RetryLogStatusEnum.SUCCESS);
        return retryLog;
    }
}