package org.walkerljl.retry.impl.defaults;

import java.util.Map;

import org.walkerljl.retry.RetryJobDispatcher;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.RunnableRetryJob;
import org.walkerljl.retry.impl.executor.RetryJobExecutor;
import org.walkerljl.retry.impl.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.impl.executor.RetryJobExecutorRepository;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;

/**
 * 默认的重试任务分发器
 *
 * @author xingxun
 */
public class DefaultRetryJobDispatcher implements RetryJobDispatcher {

    private static final Logger DETAIL_LOGGER       = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_DISPATCHER_DETAIL);
    private static final Logger DIGEST_LOGGER       = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_DISPATCHER_DIGEST);
    private static final String DEFAULT_EXECUTOR_NAME = "default";
    private static final String DEFAULT_EXECUTOR_ID = "default";

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
            RetryContext retryContext = new RetryContext();
            RetryJobExecutorConfig exectuorConfig = getExectuorConfig(retryJob);
            retryContext.setAttribute(RetryContext.NAME, getExecutorName(exectuorConfig, retryJob));
            retryContext.setAttribute(RetryContext.EXECUTOR_ID, getExecutorId(exectuorConfig, retryJob));
            retryContext.setAttribute(RetryContext.EXECUTOR_CONFIG, exectuorConfig);
            retryContext.setAttribute(RetryContext.RETRY_CONFIG, retryConfig);

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

    private RetryJobExecutorConfig getExectuorConfig(RetryJob retryJob) {
        Map<String, RetryJobExecutorConfig> exectuorConfig = retryConfig.getRetryJobExecutorConfigMap();
        return exectuorConfig == null ? new RetryJobExecutorConfig() :
                exectuorConfig.get(retryJob.getStatus().getCode());
    }

    private String getExecutorId(RetryJobExecutorConfig executorConfig, RetryJob retryJob) {
        return executorConfig == null ? DEFAULT_EXECUTOR_ID : executorConfig.getId();
    }

    private String getExecutorName(RetryJobExecutorConfig executorConfig, RetryJob retryJob) {
        return executorConfig == null ? DEFAULT_EXECUTOR_NAME : executorConfig.getName();
    }
}