package org.walkerljl.retry.impl.util;

import java.util.Date;
import java.util.List;

import org.walkerljl.retry.impl.RetryContext;
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

    public static RetryConfig getRetryConfig(RetryContext retryContext) {
        return (RetryConfig) retryContext.getAttribute(RetryContext.RETRY_CONFIG);
    }

    public static List<RetryListener> getListeners(RetryConfig retryConfig) {
        return retryConfig.getListeners();
    }
}