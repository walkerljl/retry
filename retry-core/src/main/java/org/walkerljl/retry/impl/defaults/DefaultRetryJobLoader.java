package org.walkerljl.retry.impl.defaults;

import java.util.List;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.log.invocation.InvocationInfo;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.log.logger.LoggerNames;
import org.walkerljl.retry.log.util.LoggerDetailUtil;
import org.walkerljl.retry.log.util.LoggerDigestUtil;
import org.walkerljl.retry.log.util.LoggerUtil;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.abstracts.AbstractMachine;
import org.walkerljl.retry.support.RetryJobLoader;
import org.walkerljl.retry.util.CollectionUtil;

/**
 * DefaultRetryJobLoader
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
        InvocationInfo<Void> invocationInfo = new InvocationInfo<Void>(getClass(), "load");
        try {
            this.remoteRetryJobQueue = remoteRetryJobQueue;
            int pageSize = retryConfig.getJobLoadPageSize();
            if (pageSize > retryConfig.getMaxJobAmountPerLoad()) {
                pageSize = retryConfig.getMaxJobAmountPerLoad();
            }

            int failureRetryJobCounter = 0;
            failureRetryJobCounter = loadFailureRetryJobs(failureRetryJobCounter, retryConfig.getJobLoadBeginPageNumber(), pageSize);
            if (LOGGER.isInfoEnabled()) {
                LoggerUtil.info(LOGGER, String.format("Success loaded %s failure retry jobs.", failureRetryJobCounter));
            }

            int timeoutRetryJobCounter = 0;
            timeoutRetryJobCounter = loadTimeoutRetryJobs(timeoutRetryJobCounter, retryConfig.getJobLoadBeginPageNumber(), pageSize);
            if (LOGGER.isInfoEnabled()) {
                LoggerUtil.info(LOGGER, String.format("Success loaded %s timeout retry jobs.", timeoutRetryJobCounter));
            }

            int unprocessRetryJobCounter = 0;
            unprocessRetryJobCounter = loadUnprocessRetryJobs(unprocessRetryJobCounter, retryConfig.getJobLoadBeginPageNumber(), pageSize);
            if (LOGGER.isInfoEnabled()) {
                LoggerUtil.info(LOGGER, String.format("Success loaded %s unprocess retry jobs.", unprocessRetryJobCounter));
            }

            invocationInfo.setSuccess();
        } catch (Throwable e) {
            LoggerUtil.error(LOGGER, e, "Fail to load some retry jobs.");
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
        List<RetryJob> retryJobs = retryService.listFailureRetryJobsByPage(retryConfig.getJobLoadInterval(), currentPage, pageSize);
        if (retryJobs == null || retryJobs.isEmpty()) {
            return failureRetryJobCounter;
        }
        addRetryJobsToRemoteRetryJobQueue(retryJobs);
        failureRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (failureRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobAmountPerLoad());
        if (!isContinueList) {
            return failureRetryJobCounter;
        }
        return loadFailureRetryJobs(failureRetryJobCounter, currentPage + LOAD_PAGE_STEP, pageSize);
    }

    private int loadTimeoutRetryJobs(int timeoutRetryJobCounter, int currentPage, int pageSize) {
        List<RetryJob> retryJobs = retryService.listTimeoutRetryJobsByPage(retryConfig.getJobLoadInterval(),
                retryConfig.getJobRetryTimeout(),
                currentPage, pageSize);
        if (retryJobs == null || retryJobs.isEmpty()) {
            return timeoutRetryJobCounter;
        }
        addRetryJobsToRemoteRetryJobQueue(retryJobs);
        timeoutRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (timeoutRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobAmountPerLoad());
        if (!isContinueList) {
            return timeoutRetryJobCounter;
        }
        return loadTimeoutRetryJobs(timeoutRetryJobCounter, currentPage + LOAD_PAGE_STEP, pageSize);
    }

    private int loadUnprocessRetryJobs(int unprocessRetryJobCounter, int currentPage, int pageSize) {
        List<RetryJob> retryJobs = retryService.listUnprocessRetryJobsByPage(retryConfig.getJobLoadInterval(),
                currentPage, pageSize);
        if (retryJobs == null || retryJobs.isEmpty()) {
            return unprocessRetryJobCounter;
        }
        addRetryJobsToRemoteRetryJobQueue(retryJobs);
        unprocessRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (unprocessRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobAmountPerLoad());
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
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }
}