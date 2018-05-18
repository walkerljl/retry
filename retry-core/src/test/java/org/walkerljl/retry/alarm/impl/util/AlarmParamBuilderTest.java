package org.walkerljl.retry.alarm.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.alarm.AlarmInfo;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.util.RetryUtil;
import org.walkerljl.retry.model.RetryJob;

/**
 *
 * @author xingxun
 */
public class AlarmParamBuilderTest extends BaseUnitTest {

    @Test
    public void buildAlarmInfo() {
        RetryContext retryContext = new RetryContext();
        RetryJob retryJob = new RetryJob();

        AlarmInfo actual = AlarmParamBuilder.buildAlarmInfo(null, retryJob);
        Assert.assertNull(actual);
        AlarmParamBuilder.buildAlarmInfo(retryContext, null);
        Assert.assertNull(actual);

        actual = AlarmParamBuilder.buildAlarmInfo(retryContext, retryJob);
        AlarmInfo expected = buildAlarmInfo(retryContext, retryJob);
        Assert.assertEquals(actual.getKey(), expected.getKey());
        Assert.assertEquals(actual.getDescription(), expected.getDescription());
    }

    private AlarmInfo buildAlarmInfo(RetryContext retryContext, RetryJob retryJob) {
        String retryJobIdentifier = RetryUtil.buildIdentifier(retryJob.getBizType(), retryJob.getBizId());
        Throwable e = (Throwable) retryContext.getAttribute(RetryContext.RETRY_THROABLE);
        AlarmInfo alarmInfo = new AlarmInfo(retryJobIdentifier, (e == null ? "" : e.getMessage()));

        return alarmInfo;
    }
}