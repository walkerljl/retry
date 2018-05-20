package org.walkerljl.retry.alarm.impl;

import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.model.RetryJob;

/**
 * LoggerAlarmRetryListenerTest
 *
 * @author xingxun
 */
public class LoggerAlarmRetryListenerTest extends BaseUnitTest {


    @Test
    public void onRunning() {

        LoggerAlarmRetryListener loggerAlarmRetryListener = new LoggerAlarmRetryListener();

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        loggerAlarmRetryListener.onRunning(null, retryJob);
        loggerAlarmRetryListener.onRunning(retryContext, null);
        loggerAlarmRetryListener.onRunning(retryContext, retryJob);
    }

    @Test
    public void onCompleted() {

        LoggerAlarmRetryListener loggerAlarmRetryListener = new LoggerAlarmRetryListener();

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();
        loggerAlarmRetryListener.onCompleted(null, retryJob);
        loggerAlarmRetryListener.onCompleted(retryContext, null);
        loggerAlarmRetryListener.onCompleted(retryContext, retryJob);

    }

    @Test
    public void onError() {

        LoggerAlarmRetryListener loggerAlarmRetryListener = new LoggerAlarmRetryListener();

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();
        loggerAlarmRetryListener.onError(null, retryJob);
        loggerAlarmRetryListener.onError(retryContext, null);
        loggerAlarmRetryListener.onError(retryContext, retryJob);

    }

    @Test
    public void onAbort() {

        LoggerAlarmRetryListener loggerAlarmRetryListener = new LoggerAlarmRetryListener();

        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();
        loggerAlarmRetryListener.onError(null, retryJob);
        loggerAlarmRetryListener.onError(retryContext, null);
        loggerAlarmRetryListener.onError(retryContext, retryJob);

    }
}