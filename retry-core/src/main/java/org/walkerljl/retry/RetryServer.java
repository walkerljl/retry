package org.walkerljl.retry;

import org.walkerljl.retry.impl.RetryJobLoader;
import org.walkerljl.retry.standard.Machine;

/**
 * 重试服务器
 *
 * @author xingxun
 */
public interface RetryServer extends Machine {

    /**
     * 获取重试任务加载器
     *
     * @return
     */
    RetryJobLoader getRetryJobLoader();

    /**
     * 获取重试任务分发器
     *
     * @return
     */
    RetryJobDispatcher getRetryJobDispatcher();
}