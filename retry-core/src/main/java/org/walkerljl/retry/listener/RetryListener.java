package org.walkerljl.retry.listener;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.impl.RetryContext;

/**
 * Retry listener
 *
 * @author xingxun
 */
public interface RetryListener {

    /**
     * Response to retry job which is running.
     *
     * @param retryContext Retry context
     * @param retryJob Retry job
     */
    void onRunning(RetryContext retryContext, RetryJob retryJob);

    /**
     * Response to retry job which has completed.
     *
     * @param retryContext Retry context
     * @param retryJob Retry job
     */
    void onCompleted(RetryContext retryContext, RetryJob retryJob);

    /**
     * Response to retry job which has failure.
     *
     * @param retryContext Retry context
     * @param retryJob Retry job
     */
    void onError(RetryContext retryContext, RetryJob retryJob);

    /**
     * Response to retry job which has abort.
     *
     * @param retryContext Retry context
     * @param retryJob Retry job
     */
    void onAbort(RetryContext retryContext, RetryJob retryJob);
}