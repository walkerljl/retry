package org.walkerljl.retry.listener.impl;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.impl.util.JSONUtil;
import org.walkerljl.retry.impl.util.RetryUtil;
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

    private String buildDigestLogContent(String keyWord, RetryJob retryJob) {
        String digestLogContent = String.format("[%s]%s", keyWord, RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId()));
        return digestLogContent;
    }

    private String buildDetailLogContent(String keyWord, RetryJob retryJob, RetryContext retryContext) {
        String detailLogContent = String.format("[%s](%s)(%s)",
                keyWord,
                JSONUtil.toJSONString(retryJob),
                JSONUtil.toJSONString(retryContext));
        return detailLogContent;
    }

    private void doLog(String keyWord, RetryContext retryContext, RetryJob retryJob) {
        if (DIGEST_LOGGER.isInfoEnabled()) {
            String digestLogContent = buildDigestLogContent(keyWord, retryJob);
            LoggerUtil.info(DIGEST_LOGGER, digestLogContent);
        }
        if (DETAIL_LOGGER.isInfoEnabled()) {
            String detailLogContent = buildDetailLogContent(keyWord, retryJob, retryContext);
            LoggerUtil.info(DETAIL_LOGGER, detailLogContent);
        }
    }
}