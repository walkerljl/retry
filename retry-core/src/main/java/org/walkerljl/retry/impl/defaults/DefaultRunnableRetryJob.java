package org.walkerljl.retry.impl.defaults;

import java.util.List;

import org.walkerljl.retry.RetryHandler;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.exception.RetryException;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.RetryJobHandlerRepository;
import org.walkerljl.retry.impl.RunnableRetryJob;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.impl.util.RetryObjectBuilder;
import org.walkerljl.retry.impl.util.RetryUtil;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.listener.impl.RetryListenerUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.UnlockRetryJobParam;

/**
 *  默认可执行的重试任务
 *
 * @author xingxun
 */
public class DefaultRunnableRetryJob implements RunnableRetryJob {

    /** Logger*/
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY);

    /** 重试上下文*/
    private RetryContext retryContext;
    /** 重试任务*/
    private RetryJob     retryJob;
    /** 重试业务接口*/
    private RetryService retryService;

    /**
     * 构造函数
     *
     * @param retryContext 重试上下文
     * @param retryJob 重试任务
     * @param retryService 重试业务接口
     */
    public DefaultRunnableRetryJob(RetryContext retryContext,
                                   RetryJob retryJob, RetryService retryService) {
        this.retryContext = retryContext;
        this.retryJob = retryJob;
        this.retryService = retryService;
    }

    @Override
    public void run() {

        if (retryJob == null) {
            return;
        }

        boolean isSuccess = false;
        Throwable errorThroable = null;
        List<RetryListener> retryListeners = null;
        try {
            retryListeners = RetryUtil.getListeners(RetryUtil.getRetryConfig(retryContext));

            //重新捞取任务做业务判断
            retryJob = retryService.getRetryJobById(retryJob.getId());
            if (retryJob == null) {
                return;
            }
            boolean isCanExecute = RetryUtil.isCanExecute(retryJob, RetryUtil.getRetryConfig(retryContext));
            if (!isCanExecute) {
                RetryListenerUtil.doOnAbortInterceptors(retryListeners, retryContext, retryJob);
                return;
            }

            //执行拦截器-执行中
            RetryListenerUtil.doOnRunningInterceptors(retryListeners, retryContext, retryJob);

            //锁定重试任务
            boolean isLocked = lock();
            if (isLocked) {
                return;
            }

            //执行重试任务
            process();

            //执行拦截器-成功执行
            RetryListenerUtil.doOnCompletedInterceptors(retryListeners, retryContext, retryJob);

            //标注成功
            isSuccess = true;
        } catch (Exception e) {
            errorThroable = e;
            //执行拦截器-执行错误
            RetryListenerUtil.doOnErrorInterceptors(retryListeners, retryContext, retryJob);
        } finally {
            //解锁重试任务并记录日志
            unlockRetryJobAndRecordLog(retryJob, isSuccess, errorThroable);
        }
    }

    /**
     * 锁定重试任务
     *
     * @return
     */
    private boolean lock() {
        boolean isLocked = false;
        try {
            isLocked = lockRetryJob(retryJob);
            if (!isLocked) {
                if (LOGGER.isInfoEnabled()) {
                    LoggerUtil.info(LOGGER, String.format("[%s]Fail to lock the retry job.",
                            RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId())));
                }
            }
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to lock the retry job.",
                    RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId())));
        }
        return isLocked;
    }

    /**
     * 执行重试任务
     */
    private void process() {
        //查找重试Handler
        RetryHandler retryHandler = RetryJobHandlerRepository.lookup(retryJob.getTargetIdentifer());
        if (retryHandler == null) {
            throw new RetryException(String.format("[%s]No retry handler.",
                    RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId())));
        }

        //设置重试任务参数
        retryContext.setAttribute(RetryContext.BUSINESS_PARAMS, retryJob.getParams());
        //执行重试业务
        retryHandler.retry(retryContext);
    }

    /**
     * 解锁重试任务并记录日志
     *
     * @param retryJob
     * @param isSuccess
     * @param errorThroable
     */
    private void unlockRetryJobAndRecordLog(RetryJob retryJob, boolean isSuccess, Throwable errorThroable) {
        if (retryJob == null) {
            return;
        }
        //释放重试任务
        try {
            boolean isUnlocked = unlockRetryJob(retryJob, isSuccess);
            if (!isUnlocked) {
                if (LOGGER.isInfoEnabled()) {
                    LoggerUtil.info(LOGGER, String.format("[%s]Fail to unlock the retry job.",
                            RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId())));
                }
                return;
            }
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to unlock the retry job.",
                    RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId())));
        }

        //记录重试日志
        try {
            buildAndRecordRetryLog(retryJob, isSuccess, errorThroable);
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, e, String.format("[%s]Fail to record execute log the retry job.",
                    RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId())));
        }
    }

    /**
     * 解锁重试任务
     *
     * @param retryJob 重试任务
     * @param isSuccess 是否成功，true：成功，false：失败
     */
    private boolean unlockRetryJob(RetryJob retryJob, boolean isSuccess) {
        UnlockRetryJobParam unlockRetryJobParam = RetryObjectBuilder.buildUnlockRetryJobParam(retryJob, isSuccess);
        return retryService.unlockRetryJob(unlockRetryJobParam);
    }

    /**
     * 锁定重试任务
     *
     * @param retryJob 重试任务
     * @return
     */
    private boolean lockRetryJob(RetryJob retryJob) {
        LockRetryJobParam lockRetryJobParam = RetryObjectBuilder.buildLockRetryJobParam(retryJob);
        return retryService.lockRetryJob(lockRetryJobParam);
    }

    /**
     * 构建并记录重试日志
     *
     * @param retryJob 重试任务
     * @param isSuccess 是否成功
     * @param e
     */
    private void buildAndRecordRetryLog(RetryJob retryJob, boolean isSuccess, Throwable e) {
        String errorMsg = (e == null ? "" : e.getMessage());
        RetryLog retryLog = (isSuccess ? RetryObjectBuilder.buildSuccessRetryLog(retryJob)
                : RetryObjectBuilder.buildFailureRetryLog(retryJob, errorMsg));
        retryService.saveRetryLog(retryLog);
    }
}