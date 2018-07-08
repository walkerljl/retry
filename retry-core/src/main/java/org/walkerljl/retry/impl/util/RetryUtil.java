package org.walkerljl.retry.impl.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;

/**
 * Retry util
 *
 * @author xingxun
 */
public class RetryUtil {

    /**
     * Build identifer
     *
     * @param bizType Business type
     * @param bizId Business id
     * @return
     */
    public static String buildIdentifier(String bizType, String bizId) {
        return String.format("%s:%s", bizType, bizId);
    }

    /**
     * 是否可以执行
     *
     * @param retryJob
     * @param retryConfig
     * @return
     */
    public static boolean isCanExecute(RetryJob retryJob, RetryConfig retryConfig) {
        if (retryJob == null) {
            return false;
        }
        RetryJobStatusEnum retryJobStatus = retryJob.getStatus();
        if (retryJobStatus == null) {
            return false;
        }
        //被删除的任务，不执行
        if (retryJobStatus == RetryJobStatusEnum.DELETED) {
            return false;
        }
        //超过最大重试次数，不执行
        boolean isMaxAttemptsLimit = NumUtil.intValue(retryJob.getAttempts()) >= NumUtil.intValue(retryJob.getMaxAttempts());
        if (isMaxAttemptsLimit) {
            return false;
        }

        //没执行的任务，执行
        if (retryJobStatus == RetryJobStatusEnum.UNPROCESS) {
            return true;
        }
        //上次执行失败的任务，执行
        if (retryJobStatus == RetryJobStatusEnum.FAILURE) {
            return true;
        }
        //任务正在运行中且超时
        if (retryJobStatus == RetryJobStatusEnum.PROCESSING) {
            Date lastRetryTime = retryJob.getLastRetryTime();
            if (lastRetryTime == null) {
                return true;
            }
            //使用默认配置
            if (retryConfig == null) {
                retryConfig = new RetryConfig();
            }
            boolean isTimeout = (lastRetryTime.getTime() + NumUtil.longValue(retryConfig.getJobRetryTimeout()))
                    >= System.currentTimeMillis();
            boolean isCanExecute = isTimeout;
            return isCanExecute;
        }
        return false;
    }

    /**
     * getRetryConfig
     *
     * @param retryContext
     * @return
     */
    public static RetryConfig getRetryConfig(RetryContext retryContext) {
        return retryContext == null ? null : (RetryConfig) retryContext.getAttribute(RetryContext.RETRY_CONFIG);
    }

    /**
     * getListeners
     *
     * @param retryConfig
     * @return
     */
    public static List<RetryListener> getListeners(RetryConfig retryConfig) {
        return retryConfig == null ? null : retryConfig.getListeners();
    }

    /**
     * buildRetryContext
     *
     * @param retryConfig
     * @param retryJob
     * @return
     */
    public static RetryContext buildRetryContext(RetryConfig retryConfig, RetryJob retryJob) {
        if (retryConfig == null) {
            return null;
        }
        if (retryJob == null) {
            return null;
        }
        RetryContext retryContext = new RetryContext();
        RetryJobExecutorConfig exectuorConfig = getExectuorConfig(retryConfig, retryJob);
        retryContext.setAttribute(RetryContext.NAME, exectuorConfig.getName());
        retryContext.setAttribute(RetryContext.EXECUTOR_ID, exectuorConfig.getId());
        retryContext.setAttribute(RetryContext.EXECUTOR_CONFIG, exectuorConfig);
        retryContext.setAttribute(RetryContext.RETRY_CONFIG, retryConfig);

        return retryContext;
    }

    /**
     * getExectuorConfig
     *
     * @param retryConfig
     * @param retryJob
     * @return
     */
    public static RetryJobExecutorConfig getExectuorConfig(RetryConfig retryConfig, RetryJob retryJob) {
        if (retryJob == null) {
            return null;
        }
        if (retryJob.getStatus() == null) {
            return null;
        }
        Map<String, RetryJobExecutorConfig> exectuorConfig =
                retryConfig == null ? null : retryConfig.getRetryJobExecutorConfigMap();
        return exectuorConfig == null ? new RetryJobExecutorConfig() :
                exectuorConfig.get(retryJob.getStatus().getCode());
    }

    public static void addRetryJobsToRemoteRetryJobQueue(List<RetryJob> retryJobs, RemoteRetryJobQueue remoteRetryJobQueue) {
        if (CollectionUtil.isEmpty(retryJobs)) {
            return;
        }
        if (remoteRetryJobQueue == null) {
            return;
        }
        for (RetryJob retryJob : retryJobs) {
            if (retryJob == null) {
                continue;
            }
            remoteRetryJobQueue.addRetryJob(retryJob);
        }
    }

    public static int loadFailureRetryJobs(RetryService retryService,
                                           RetryConfig retryConfig,
                                           RemoteRetryJobQueue remoteRetryJobQueue,
                                           int failureRetryJobCounter,
                                           int currentPage,
                                           int pageSize) {
        List<RetryJob> retryJobs = retryService.listFailureRetryJobs(retryConfig.getJobLoadInterval(), currentPage, pageSize);
        if (CollectionUtil.isEmpty(retryJobs)) {
            return failureRetryJobCounter;
        }
        RetryUtil.addRetryJobsToRemoteRetryJobQueue(retryJobs, remoteRetryJobQueue);
        failureRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (failureRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobQuantityPerLoad());
        if (!isContinueList) {
            return failureRetryJobCounter;
        }
        return loadFailureRetryJobs(retryService,
                retryConfig,
                remoteRetryJobQueue,
                failureRetryJobCounter,
                currentPage + RetryConstants.LOAD_PAGE_STEP, pageSize);
    }

    public static int loadTimeoutRetryJobs(RetryService retryService,
                                     RetryConfig retryConfig,
                                     RemoteRetryJobQueue remoteRetryJobQueue,
                                     int timeoutRetryJobCounter,
                                     int currentPage,
                                     int pageSize) {
        List<RetryJob> retryJobs = retryService.listTimeoutRetryJobs(retryConfig.getJobLoadInterval(),
                retryConfig.getJobRetryTimeout(),
                currentPage, pageSize);
        if (CollectionUtil.isEmpty(retryJobs)) {
            return timeoutRetryJobCounter;
        }
        RetryUtil.addRetryJobsToRemoteRetryJobQueue(retryJobs, remoteRetryJobQueue);
        timeoutRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (timeoutRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobQuantityPerLoad());
        if (!isContinueList) {
            return timeoutRetryJobCounter;
        }
        return loadTimeoutRetryJobs(retryService,
                retryConfig,
                remoteRetryJobQueue,
                timeoutRetryJobCounter,
                currentPage + RetryConstants.LOAD_PAGE_STEP,
                pageSize);
    }

    public static int loadUnprocessRetryJobs(RetryService retryService,
                                       RetryConfig retryConfig,
                                       RemoteRetryJobQueue remoteRetryJobQueue,
                                       int unprocessRetryJobCounter,
                                       int currentPage,
                                       int pageSize) {
        List<RetryJob> retryJobs = retryService.listUnprocessRetryJobs(retryConfig.getJobLoadInterval(),
                currentPage, pageSize);
        if (CollectionUtil.isEmpty(retryJobs)) {
            return unprocessRetryJobCounter;
        }
        RetryUtil.addRetryJobsToRemoteRetryJobQueue(retryJobs, remoteRetryJobQueue);
        unprocessRetryJobCounter += retryJobs.size();

        boolean isContinueList = (retryJobs.size() == pageSize && (unprocessRetryJobCounter + pageSize) <= retryConfig
                .getMaxJobQuantityPerLoad());
        if (!isContinueList) {
            return unprocessRetryJobCounter;
        }
        return loadUnprocessRetryJobs(retryService,
                retryConfig,
                remoteRetryJobQueue,
                unprocessRetryJobCounter,
                currentPage + RetryConstants.LOAD_PAGE_STEP,
                pageSize);
    }
}