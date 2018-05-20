package org.walkerljl.retry.impl;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.standard.machine.Machine;

/**
 * 重试任务加载器
 *
 * @author xingxun
 */
public interface RetryJobLoader extends Machine {

    /**
     * 从远程队列加载重试任务
     *
     * @param remoteRetryJobQueue 远程重试任务队列
     */
    void load(RemoteRetryJobQueue remoteRetryJobQueue);
}