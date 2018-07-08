package org.walkerljl.retry.impl.defaults;

import org.walkerljl.retry.RetryJobDispatcher;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.impl.RetryConstants;
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
import org.walkerljl.retry.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;

/**
 * 默认的重试任务分发器
 *
 * @author xingxun
 */
public class DefaultRetryJobDispatcher extends AbstractMachine implements RetryJobDispatcher {

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
        if (!isRunning()) {
            return;
        }
        InvocationInfo<Object[], RetryJobExecutor> invocationInfo = new InvocationInfo<>(getClass(), "dispatch",
                new Object[]{isRunning(), retryConfig, retryJob});
        try {
            RetryJobExecutor retryJobExecutor = null;
            if (retryJob != null) {
                RetryContext retryContext = RetryUtil.buildRetryContext(retryConfig, retryJob);
                RunnableRetryJob runnableRetryJob = new DefaultRunnableRetryJob(retryContext, retryJob, retryService);

                retryJobExecutor = RetryJobExecutorRepository.getInstance().lookup(retryContext);
                if (retryJobExecutor != null) {
                    retryJobExecutor.execute(runnableRetryJob);
                }
            }
            invocationInfo.markSuccess(retryJobExecutor);
        } catch (Exception e) {
            invocationInfo.markFailure(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public String getGroup() {
        return RetryConstants.COMPONENT_IDENTIFIER_JOB_DISPATCHER;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }
}