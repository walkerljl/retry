package org.walkerljl.retry.listener.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.listener.impl.DummyRetryListener;
import org.walkerljl.retry.listener.impl.ThrowableRetryListener;
import org.walkerljl.retry.model.RetryJob;

/**
 *
 * @author xingxun
 */
public class RetryListenerUtilTest extends BaseUnitTest {

    @Test
    public void doOnRunningInterceptors() {

        List<RetryListener> retryListeners = buildTestRetryListeners();
        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        RetryListenerUtil.doOnRunningInterceptors(null, retryContext, retryJob);
        RetryListenerUtil.doOnRunningInterceptors(retryListeners, null, retryJob);
        RetryListenerUtil.doOnRunningInterceptors(retryListeners, retryContext, null);
        RetryListenerUtil.doOnRunningInterceptors(retryListeners, retryContext, retryJob);
    }

    @Test
    public void doOnCompletedInterceptors() {

        List<RetryListener> retryListeners = buildTestRetryListeners();
        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        RetryListenerUtil.doOnCompletedInterceptors(null, retryContext, retryJob);
        RetryListenerUtil.doOnCompletedInterceptors(retryListeners, null, retryJob);
        RetryListenerUtil.doOnCompletedInterceptors(retryListeners, retryContext, null);
        RetryListenerUtil.doOnCompletedInterceptors(retryListeners, retryContext, retryJob);    }

    @Test
    public void doOnErrorInterceptors() {

        List<RetryListener> retryListeners = buildTestRetryListeners();
        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        RetryListenerUtil.doOnErrorInterceptors(null, retryContext, retryJob);
        RetryListenerUtil.doOnErrorInterceptors(retryListeners, null, retryJob);
        RetryListenerUtil.doOnErrorInterceptors(retryListeners, retryContext, null);
        RetryListenerUtil.doOnErrorInterceptors(retryListeners, retryContext, retryJob);    }

    @Test
    public void doOnAbortInterceptors() {

        List<RetryListener> retryListeners = buildTestRetryListeners();
        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        RetryListenerUtil.doOnAbortInterceptors(null, retryContext, retryJob);
        RetryListenerUtil.doOnAbortInterceptors(retryListeners, null, retryJob);
        RetryListenerUtil.doOnAbortInterceptors(retryListeners, retryContext, null);
        RetryListenerUtil.doOnAbortInterceptors(retryListeners, retryContext, retryJob);    }

    private List<RetryListener> buildTestRetryListeners() {
        List<RetryListener> retryListeners = new ArrayList<>(1);
        retryListeners.add(new DummyRetryListener());
        retryListeners.add(null);
        retryListeners.add(new ThrowableRetryListener());

        return retryListeners;
    }
}