package org.walkerljl.retry.impl.defaults;

import org.walkerljl.retry.RetryJobFetcher;
import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.log.invocation.InvocationInfo;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.log.logger.LoggerNames;
import org.walkerljl.retry.log.util.LoggerDetailUtil;
import org.walkerljl.retry.log.util.LoggerDigestUtil;
import org.walkerljl.retry.log.util.LoggerUtil;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.abstracts.AbstractMachine;
import org.walkerljl.retry.support.RetryJobDispatcher;

/**
 * DefaultRetryJobFetcher
 *
 * @author xingxun
 */
public class DefaultRetryJobFetcher extends AbstractMachine implements RetryJobFetcher {

    private static final Logger LOGGER        = LoggerFactory.getLogger(DefaultRetryJobFetcher.class);
    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_FETCHER_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_JOB_FETCHER_DETAIL);

    private RetryJobDispatcher retryJobDispatcher;

    public DefaultRetryJobFetcher(RetryJobDispatcher retryJobDispatcher) {
        this.retryJobDispatcher = retryJobDispatcher;
    }

    @Override
    public void onFetch(RetryJob retryJob) {
        InvocationInfo<Void> invocationInfo = new InvocationInfo<Void>(getClass(), "onFetch", new Object[] {retryJob});
        try {
            retryJobDispatcher.dispatch(retryJob);
            invocationInfo.setSuccess();
        } catch (Throwable e) {
            invocationInfo.setFailure(e);
            throw e;
        } finally {
            try {
                LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
                LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e);
            }
        }
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }
}