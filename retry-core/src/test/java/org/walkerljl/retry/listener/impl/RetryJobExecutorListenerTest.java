package org.walkerljl.retry.listener.impl;

import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryJob;

/**
 *
 * @author xingxun
 */
public class RetryJobExecutorListenerTest extends BaseUnitTest {

    private RetryListener retryListener = new RetryJobExecutorListener();

    @Test
    public void onRunning() {

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        retryListener.onRunning(null, retryJob);
        retryListener.onRunning(retryContext, null);
        retryListener.onRunning(retryContext, retryJob);
    }

    @Test
    public void onCompleted() {

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        retryListener.onCompleted(null, retryJob);
        retryListener.onCompleted(retryContext, null);
        retryListener.onCompleted(retryContext, retryJob);
    }

    @Test
    public void onError() {

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        retryListener.onError(null, retryJob);
        retryListener.onError(retryContext, null);
        retryListener.onError(retryContext, retryJob);
    }

    @Test
    public void onAbort() {

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        retryListener.onAbort(null, retryJob);
        retryListener.onAbort(retryContext, null);
        retryListener.onAbort(retryContext, retryJob);
    }
}