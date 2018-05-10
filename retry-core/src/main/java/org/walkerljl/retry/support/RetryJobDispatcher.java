package org.walkerljl.retry.support;

import org.walkerljl.retry.model.RetryJob;

/**
 * Retry job dispatcher
 *
 * @author xingxun
 */
public interface RetryJobDispatcher {

    /**
     * Job dispatch
     *
     * @param retryJob Retry job
     */
    void dispatch(RetryJob retryJob);
}