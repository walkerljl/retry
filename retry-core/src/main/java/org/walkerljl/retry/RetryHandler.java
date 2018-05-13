package org.walkerljl.retry;

import org.walkerljl.retry.impl.RetryContext;

/**
 * 重试处理器
 *
 * @author xingxun
 */
public interface RetryHandler {

    /**
     * 重试
     *
     * @param context 重试上下文
     */
    void retry(RetryContext context);
}