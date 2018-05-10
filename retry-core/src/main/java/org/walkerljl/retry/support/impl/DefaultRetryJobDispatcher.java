package org.walkerljl.retry.support.impl;

import java.util.Map;

import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.executor.RetryJobExecutor;
import org.walkerljl.retry.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.executor.RetryJobExecutorRepository;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.support.RetryContext;
import org.walkerljl.retry.support.RetryJobDispatcher;
import org.walkerljl.retry.support.RunnableRetryJob;
import org.walkerljl.retry.util.RetryUtil;

/**
 * DefaultRetryJobDispatcher
 *
 * @author xingxun
 */
public class DefaultRetryJobDispatcher implements RetryJobDispatcher {

    private static final Logger LOGGER              = LoggerFactory.getLogger(DefaultRetryJobDispatcher.class);
    private static final String DEFAULT_EXECUTOR_ID = "default";

    private RetryConfig  retryConfig;
    private RetryService retryService;

    public DefaultRetryJobDispatcher(RetryConfig retryConfig, RetryService retryService) {
        this.retryConfig = retryConfig;
        this.retryService = retryService;
    }

    @Override
    public void dispatch(RetryJob retryJob) {
        try {
            String retryJobIdentifier = RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId());
            RetryContext retryContext = new RetryContext();
            retryContext.setAttribute(RetryContext.NAME, retryJobIdentifier);
            retryContext.setAttribute(RetryContext.EXECUTOR_ID, getExecutorId(retryJob));
            RetryJobExecutorConfig retryJobExecutorConfig = getExectuorConfig(retryJob);
            if (retryJobExecutorConfig == null) {
                retryJobExecutorConfig = new RetryJobExecutorConfig();
            }
            retryContext.setAttribute(RetryContext.EXECUTOR_CONFIG, retryJobExecutorConfig);
            retryContext.setAttribute(RetryContext.RETRY_CONFIG, retryConfig);

            RunnableRetryJob runnableRetryJob = new DefaultRunnableRetryJob(retryContext, retryJob, retryService);

            RetryJobExecutor retryJobExecutor = RetryJobExecutorRepository.getInstance().lookup(retryContext);
            retryJobExecutor.execute(runnableRetryJob);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("Success dispatch one retry job:%s.", retryJob.toString()));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("Fail to dispatch one retry job:%s.", retryJob.toString()), e);
        }
    }

    private RetryJobExecutorConfig getExectuorConfig(RetryJob retryJob) {
        Map<String, RetryJobExecutorConfig> retryJobExecutorConfigMap = retryConfig.getRetryJobExecutorConfigMap();
        return retryJobExecutorConfigMap == null ? null :
                retryJobExecutorConfigMap.get(retryJob.getStatus().getCode());
    }

    private String getExecutorId(RetryJob retryJob) {
        RetryJobExecutorConfig retryJobExecutorConfig = getExectuorConfig(retryJob);
        return retryJobExecutorConfig == null ? DEFAULT_EXECUTOR_ID : retryJobExecutorConfig.getId();
    }
}