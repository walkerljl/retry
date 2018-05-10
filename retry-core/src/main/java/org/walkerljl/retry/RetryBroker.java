package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;

/**
 * Retry broker
 *
 * @author xingxun
 */
public interface RetryBroker {

    /**
     * Submit a retry job to retry service
     *
     * @param retryJob retry job
     */
    void submit(RetryJob retryJob);
}