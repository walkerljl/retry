package org.walkerljl.retry.impl.executor;

import org.walkerljl.retry.impl.RunnableRetryJob;
import org.walkerljl.retry.standard.machine.Machine;

/**
 * 重试任务执行器
 *
 * @author xingxun
 */
public interface RetryJobExecutor extends Machine {

    /**
     * 执行重试任务
     *
     * @param runnableRetryJob 可执行的重试任务
     */
    void execute(RunnableRetryJob runnableRetryJob);
}