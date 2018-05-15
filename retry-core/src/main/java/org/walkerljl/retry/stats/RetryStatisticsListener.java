package org.walkerljl.retry.stats;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryJob;

/**
 * RetryStatisticsListener
 *
 * @author xingxun
 */
public class RetryStatisticsListener implements RetryListener {

    private RetryStatisticsRepository retryStatisticsRepository;

    public RetryStatisticsListener(RetryStatisticsRepository retryStatisticsRepository) {
        this.retryStatisticsRepository = retryStatisticsRepository;
    }

    @Override
    public void onRunning(RetryContext retryContext, RetryJob retryJob) {
        retryStatisticsRepository.addRunning(getName(retryContext));
    }

    @Override
    public void onCompleted(RetryContext retryContext, RetryJob retryJob) {
        retryStatisticsRepository.addCompleted(getName(retryContext));
    }

    @Override
    public void onError(RetryContext retryContext, RetryJob retryJob) {
        retryStatisticsRepository.addError(getName(retryContext));
    }

    @Override
    public void onAbort(RetryContext retryContext, RetryJob retryJob) {
        retryStatisticsRepository.addAbort(getName(retryContext));
    }

    /**
     * Get name
     *
     * @param retryContext
     * @return
     */
    private String getName(RetryContext retryContext) {
        return (String) retryContext.getAttribute(RetryContext.NAME);
    }
}