package org.walkerljl.retry.executor;

import org.walkerljl.retry.support.RunnableRetryJob;

/**
 * Retry job executor
 *
 * @author xingxun
 */
public interface RetryJobExecutor {

    /**
     * Execute retry job
     *
     * @param runnableRetryJob Runnable retry job
     */
    void execute(RunnableRetryJob runnableRetryJob);
}