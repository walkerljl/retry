package org.walkerljl.retry;

import org.walkerljl.retry.support.RetryContext;

/**
 * Retry handler
 *
 * @author xingxun
 */
public interface RetryHandler {

    /**
     * Do retry
     *
     * @param context Retry context
     */
    void retry(RetryContext context);
}