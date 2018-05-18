package org.walkerljl.retry.alarm.impl;

import org.walkerljl.retry.alarm.Alarm;
import org.walkerljl.retry.alarm.AlarmInfo;
import org.walkerljl.retry.alarm.impl.util.AlarmParamBuilder;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryJob;

/**
 * 日志报警重试监听器
 *
 * @author xingxun
 */
public class LoggerAlarmRetryListener implements RetryListener {

    private Alarm alarm = new LoggerAlarm();

    @Override
    public void onRunning(RetryContext retryContext, RetryJob retryJob) {
        //do nothing.
    }

    @Override
    public void onCompleted(RetryContext retryContext, RetryJob retryJob) {
        //do nothing.
    }

    @Override
    public void onError(RetryContext retryContext, RetryJob retryJob) {
        alarm(retryContext, retryJob);
    }

    @Override
    public void onAbort(RetryContext retryContext, RetryJob retryJob) {
        alarm(retryContext, retryJob);
    }

    /**
     * alarm
     *
     * @param retryContext
     * @param retryJob
     */
    private void alarm(RetryContext retryContext, RetryJob retryJob) {
        AlarmInfo alarmInfo = AlarmParamBuilder.buildAlarmInfo(retryContext, retryJob);
        alarm.alarm(alarmInfo);
    }
}