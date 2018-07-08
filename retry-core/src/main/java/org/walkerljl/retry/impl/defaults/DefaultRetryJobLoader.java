package org.walkerljl.retry.impl.defaults;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.RetryJobLoader;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.impl.util.ArrayUtil;
import org.walkerljl.retry.impl.util.RetryUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;

/**
 * 默认的重试任务加载器
 *
 * @author xingxun
 */
public class DefaultRetryJobLoader extends AbstractMachine implements RetryJobLoader {

    private static final Logger DIGEST_LOGGER  = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_LOADER_DIGEST);
    private static final Logger DETAIL_LOGGER  = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_LOADER_DETAIL);

    private RemoteRetryJobQueue remoteRetryJobQueue;
    private RetryConfig         retryConfig;
    private RetryService        retryService;

    public DefaultRetryJobLoader(RetryConfig retryConfig, RetryService retryService) {
        this.retryConfig = retryConfig;
        this.retryService = retryService;
    }

    @Override
    public void load(RemoteRetryJobQueue remoteRetryJobQueue) {
        InvocationInfo<Object[], Object> invocationInfo =
                new InvocationInfo<>(getClass(), "load",
                        new Object[]{isRunning(), retryConfig});
        try {
            int failureRetryJobCounter = 0;
            int timeoutRetryJobCounter = 0;
            int unprocessRetryJobCounter = 0;
            if (remoteRetryJobQueue != null && isRunning()) {
                this.remoteRetryJobQueue = remoteRetryJobQueue;
                int pageSize = retryConfig.getJobLoadPageSize();
                if (pageSize > retryConfig.getMaxJobQuantityPerLoad()) {
                    pageSize = retryConfig.getMaxJobQuantityPerLoad();
                }

                //加载失败的重试任务
                failureRetryJobCounter = RetryUtil.loadFailureRetryJobs(
                        this.retryService,
                        this.retryConfig,
                        this.remoteRetryJobQueue,
                        failureRetryJobCounter,
                        retryConfig.getJobLoadBeginPageNumber(),
                        pageSize);

                //加载超时的重试任务
                timeoutRetryJobCounter = RetryUtil.loadTimeoutRetryJobs(
                        this.retryService,
                        this.retryConfig,
                        this.remoteRetryJobQueue,
                        timeoutRetryJobCounter,
                        retryConfig.getJobLoadBeginPageNumber(),
                        pageSize);

                //加载未处理的重试任务
                unprocessRetryJobCounter = RetryUtil.loadUnprocessRetryJobs(
                        this.retryService,
                        this.retryConfig,
                        this.remoteRetryJobQueue,
                        unprocessRetryJobCounter,
                        retryConfig.getJobLoadBeginPageNumber(),
                        pageSize);
            }

            invocationInfo.markSuccess(
                    ArrayUtil.concat(",", new Integer[] {failureRetryJobCounter, timeoutRetryJobCounter, unprocessRetryJobCounter}));
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
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