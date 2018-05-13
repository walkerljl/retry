package org.walkerljl.retry.alarm.impl;

import org.walkerljl.retry.alarm.Alarm;
import org.walkerljl.retry.alarm.AlarmInfo;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.util.RetryUtil;

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
        alarm(retryJob);
    }

    @Override
    public void onAbort(RetryContext retryContext, RetryJob retryJob) {
        alarm(retryJob);
    }

    /**
     * Do alarm
     *
     * @param retryJob Retry job
     */
    private void alarm(RetryJob retryJob) {
        String retryJobIdentifier = RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId());
        AlarmInfo alarmInfo = new AlarmInfo(retryJobIdentifier, retryJob.toString());
        alarm.alarm(alarmInfo);
    }
}