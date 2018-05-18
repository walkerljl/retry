package org.walkerljl.retry.listener.impl;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryJob;

/**
 *
 * @author xingxun
 */
public class ThrowableRetryListener implements RetryListener {

    @Override
    public void onRunning(RetryContext retryContext, RetryJob retryJob) {
        throw new RuntimeException("测试异常");
    }

    @Override
    public void onCompleted(RetryContext retryContext, RetryJob retryJob) {
        throw new RuntimeException("测试异常");
    }

    @Override
    public void onError(RetryContext retryContext, RetryJob retryJob) {
        throw new RuntimeException("测试异常");
    }

    @Override
    public void onAbort(RetryContext retryContext, RetryJob retryJob) {
        throw new RuntimeException("测试异常");
    }
}