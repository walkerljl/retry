package org.walkerljl.retry.impl.defaults;

import java.util.List;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.RetryJobLoader;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.impl.util.ArrayUtil;
import org.walkerljl.retry.impl.util.CollectionUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.abstracts.AbstractMachine;

/**
 * 默认的重试任务加载器
 *
 * @author xingxun
 */
public class DefaultRetryJobLoader extends AbstractMachine implements RetryJobLoader {

    private static final Logger LOGGER         = LoggerFactory.getLogger(DefaultRetryJobLoader.class);
    private static final Logger DIGEST_LOGGER  = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_LOADER_DIGEST);
    private static final Logger DETAIL_LOGGER  = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_LOADER_DETAIL);
    private static final int    LOAD_PAGE_STEP = 1;

    private RemoteRetryJobQueue remoteRetryJobQueue;
    private RetryConfig         retryConfig;
    private RetryService        retryService;

    public DefaultRetryJobLoader(RetryConfig retryConfig, RetryService retryService) {
        this.retryConfig = retryConfig;
        this.retryService = retryService;
    }

    @Override
    public void load(RemoteRetryJobQueue remoteRetryJobQueue) {
        InvocationInfo<RemoteRetryJobQueue, Void> invocationInfo = new InvocationInfo<>(getClass(), "load", remoteRetryJobQueue);
        try {
            this.remoteRetryJobQueue = remoteRetryJobQueue;
            int pageSize = retryConfig.getJobLoadPageSize();
            if (pageSize > retryConfig.getMaxJobQuantityPerLoad()) {
                pageSize = retryConfig.getMaxJobQuantityPerLoad();
            }

            //加载失败的重试任务
            int failureRetryJobCounter = 0;
            failureRetryJobCounter = loadFailureRetryJobs(failureRetryJobCounter, retryConfig.getJobLoadBeginPageNumber(), pageSize);

            //加载超时的重试任务
            int timeoutRetryJobCounter = 0;
            timeoutRetryJobCounter = loadTimeoutRetryJobs(timeoutRetryJobCounter, retryConfig.getJobLoadBeginPageNumber(), pageSize);

            //加载未处理的重试任务
            int unprocessRetryJobCounter = 0;
            unprocessRetryJobCounter = loadUnprocessRetryJobs(unprocessRetryJobCounter, retryConfig.getJobLoadBeginPageNumber(), pageSize);

            invocationInfo.markResult(true,
                    ArrayUtil.concat(",", new Integer[] {failureRetryJobCounter, timeoutRetryJobCounter, unprocessRetryJobCounter}),
                    null);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
        } finally {
            try {
                LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
                LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e);
            }
        }
    }

    private int loadFailureRetryJobs(int failureRetryJobCounter, int currentPage, int pageSize) {
        List<RetryJob> retryJobs = retryService.listFailureRetryJobs(retryConfig.getJobLoadInterval(), currentPage, pageSize);
        if (CollectionUtil.isEmpty(retryJobs)) {
            return failureRetryJobCounter;
        }
        addRetryJobsToRemoteRetryJobQueue(retryJobs);
        failureRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (failureRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobQuantityPerLoad());
        if (!isContinueList) {
            return failureRetryJobCounter;
        }
        return loadFailureRetryJobs(failureRetryJobCounter, currentPage + LOAD_PAGE_STEP, pageSize);
    }

    private int loadTimeoutRetryJobs(int timeoutRetryJobCounter, int currentPage, int pageSize) {
        List<RetryJob> retryJobs = retryService.listTimeoutRetryJobs(retryConfig.getJobLoadInterval(),
                retryConfig.getJobRetryTimeout(),
                currentPage, pageSize);
        if (CollectionUtil.isEmpty(retryJobs)) {
            return timeoutRetryJobCounter;
        }
        addRetryJobsToRemoteRetryJobQueue(retryJobs);
        timeoutRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (timeoutRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobQuantityPerLoad());
        if (!isContinueList) {
            return timeoutRetryJobCounter;
        }
        return loadTimeoutRetryJobs(timeoutRetryJobCounter, currentPage + LOAD_PAGE_STEP, pageSize);
    }

    private int loadUnprocessRetryJobs(int unprocessRetryJobCounter, int currentPage, int pageSize) {
        List<RetryJob> retryJobs = retryService.listUnprocessRetryJobs(retryConfig.getJobLoadInterval(),
                currentPage, pageSize);
        if (CollectionUtil.isEmpty(retryJobs)) {
            return unprocessRetryJobCounter;
        }
        addRetryJobsToRemoteRetryJobQueue(retryJobs);
        unprocessRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (unprocessRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobQuantityPerLoad());
        if (!isContinueList) {
            return unprocessRetryJobCounter;
        }
        return loadUnprocessRetryJobs(unprocessRetryJobCounter, currentPage + LOAD_PAGE_STEP, pageSize);
    }

    private void addRetryJobsToRemoteRetryJobQueue(List<RetryJob> retryJobs) {
        if (CollectionUtil.isEmpty(retryJobs)) {
            return;
        }
        for (RetryJob retryJob : retryJobs) {
            if (retryJob == null) {
                continue;
            }
            remoteRetryJobQueue.addRetryJob(retryJob);
        }
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public String getGroup() {
        return RetryConstants.COMPONENT_IDENTIFIER_JOB_LOADER;
    }
}