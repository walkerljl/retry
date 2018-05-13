package org.walkerljl.retry.impl.executor;

import org.walkerljl.retry.impl.RunnableRetryJob;

/**
 * 重试任务执行器
 *
 * @author xingxun
 */
public interface RetryJobExecutor {

    /**
     * 执行重试任务
     *
     * @param runnableRetryJob 可执行的重试任务
     */
    void execute(RunnableRetryJob runnableRetryJob);
}