package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;

/**
 * 远程重试队列
 *
 * @author xingxun
 */
public interface RemoteRetryJobQueue {

    /**
     * 添加重试任务
     *
     * @param retryJob 重试任务
     */
    void addRetryJob(RetryJob retryJob);
}