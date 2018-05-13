package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;

/**
 * Retry broker
 *
 * @author xingxun
 */
public interface RetryBroker {

    /**
     * 提交一个重试任务
     *
     * @param retryJob 重试任务
     */
    void submit(RetryJob retryJob);
}