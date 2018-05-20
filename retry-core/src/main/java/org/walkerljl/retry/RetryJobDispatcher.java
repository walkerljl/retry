package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.machine.Machine;

/**
 * 重试任务分发器
 *
 * @author xingxun
 */
public interface RetryJobDispatcher extends Machine {

    /**
     * 任务分发
     *
     * @param retryJob 重试任务
     */
    void dispatch(RetryJob retryJob);
}