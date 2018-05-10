package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;

/**
 * Retry waiter
 *
 * @author xingxun
 */
public interface RetryWaiter {

    /**
     * Submit a retry job to retry service
     *
     * @param retryJob retry job
     */
    void submit(RetryJob retryJob);
}