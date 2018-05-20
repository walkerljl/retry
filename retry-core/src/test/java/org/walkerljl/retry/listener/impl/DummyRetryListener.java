package org.walkerljl.retry.listener.impl;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryJob;

/**
 * DummyRetryListener
 *
 * @author xingxun
 */
public class DummyRetryListener implements RetryListener {

    @Override
    public void onRunning(RetryContext retryContext, RetryJob retryJob) {

    }

    @Override
    public void onCompleted(RetryContext retryContext, RetryJob retryJob) {

    }

    @Override
    public void onError(RetryContext retryContext, RetryJob retryJob) {

    }

    @Override
    public void onAbort(RetryContext retryContext, RetryJob retryJob) {

    }
}