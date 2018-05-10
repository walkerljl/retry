package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;

/**
 * RemoteRetryJobQueue
 *
 * @author xingxun
 */
public interface RemoteRetryJobQueue {

    /**
     * Batch add retry job
     *
     * @param retryJob Retry job
     */
    void addRetryJob(RetryJob retryJob);
}