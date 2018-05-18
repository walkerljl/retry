package org.walkerljl.retry.alarm.impl.util;

import org.walkerljl.retry.alarm.AlarmInfo;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.util.RetryUtil;
import org.walkerljl.retry.model.RetryJob;

/**
 * AlarmParamBuilder
 *
 * @author xingxun
 */
public class AlarmParamBuilder {

    /**
     * 构建报警信息
     *
     * @param retryContext
     * @param retryJob
     * @return
     */
    public static AlarmInfo buildAlarmInfo(RetryContext retryContext, RetryJob retryJob) {
        if (retryContext == null) {
            return null;
        }
        if (retryJob == null) {
            return null;
        }
        String retryJobIdentifier = RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId());
        Throwable e = (Throwable) retryContext.getAttribute(RetryContext.RETRY_THROABLE);
        AlarmInfo alarmInfo = new AlarmInfo(retryJobIdentifier, (e == null ? "" : e.getMessage()));

        return alarmInfo;
    }
}