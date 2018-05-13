package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;

/**
 * 重试任务分发器
 *
 * @author xingxun
 */
public interface RetryJobDispatcher {

    /**
     * 任务分发
     *
     * @param retryJob 重试任务
     */
    void dispatch(RetryJob retryJob);
}