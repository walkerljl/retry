package org.walkerljl.retry.listener.impl;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryJob;

/**
 * 重试任务执行监听器
 *
 * @author xingxun
 */
public class RetryJobExecutorListener implements RetryListener {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_EXECUTOR_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_EXECUTOR_DETAIL);

    @Override
    public void onRunning(RetryContext retryContext, RetryJob retryJob) {
        doLog("Running", retryContext, retryJob);
    }

    @Override
    public void onCompleted(RetryContext retryContext, RetryJob retryJob) {
        doLog("Completed", retryContext, retryJob);
    }

    @Override
    public void onError(RetryContext retryContext, RetryJob retryJob) {
        doLog("Failure", retryContext, retryJob);
    }

    @Override
    public void onAbort(RetryContext retryContext, RetryJob retryJob) {
        doLog("Aborted", retryContext, retryJob);
    }

    /**
     * doLog
     *
     * @param keyWord
     * @param retryContext
     * @param retryJob
     */
    private void doLog(String keyWord, RetryContext retryContext, RetryJob retryJob) {
        InvocationInfo<RetryJob, RetryContext> invocationInfo = new InvocationInfo<>(getClass(), keyWord, retryJob);
        invocationInfo.markSuccess(retryContext);
        LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
        LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
    }
}