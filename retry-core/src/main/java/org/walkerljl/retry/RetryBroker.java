package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.machine.Machine;

/**
 * Retry broker
 *
 * @author xingxun
 */
public interface RetryBroker extends Machine{

    /**
     * 提交一个重试任务
     *
     * @param retryJob 重试任务
     * @return 重试任务ID
     */
    String submit(RetryJob retryJob);

    /**
     * 标注重试任务已经完成
     *
     * @param retryJobId 重试任务ID
     */
    void markRetryJobToCompleted(String retryJobId);
}