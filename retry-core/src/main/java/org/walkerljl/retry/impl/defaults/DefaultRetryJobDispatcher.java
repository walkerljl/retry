package org.walkerljl.retry.impl.defaults;

import org.walkerljl.retry.RetryJobDispatcher;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.RunnableRetryJob;
import org.walkerljl.retry.impl.executor.RetryJobExecutor;
import org.walkerljl.retry.impl.executor.RetryJobExecutorRepository;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.impl.util.RetryUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;

/**
 * 默认的重试任务分发器
 *
 * @author xingxun
 */
public class DefaultRetryJobDispatcher implements RetryJobDispatcher {

    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_DISPATCHER_DETAIL);
    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_DISPATCHER_DIGEST);

    private RetryConfig  retryConfig;
    private RetryService retryService;

    public DefaultRetryJobDispatcher(RetryConfig retryConfig, RetryService retryService) {
        this.retryConfig = retryConfig;
        this.retryService = retryService;
    }

    @Override
    public void dispatch(RetryJob retryJob) {
        InvocationInfo<RetryJob, Void> invocationInfo = new InvocationInfo<>(getClass(), "dispatch", retryJob);
        try {
            RetryContext retryContext = RetryUtil.buildRetryContext(retryJob, retryConfig);
            RunnableRetryJob runnableRetryJob = new DefaultRunnableRetryJob(retryContext, retryJob, retryService);

            RetryJobExecutor retryJobExecutor = RetryJobExecutorRepository.getInstance().lookup(retryContext);
            retryJobExecutor.execute(runnableRetryJob);

            invocationInfo.markResult(true, retryJobExecutor, null);
        } catch (Exception e) {
            invocationInfo.markFailure(e);
        } finally {
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
        }
    }
}