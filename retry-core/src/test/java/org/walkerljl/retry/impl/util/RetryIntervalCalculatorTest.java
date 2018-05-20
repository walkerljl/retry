package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * RetryIntervalCalculatorTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryIntervalCalculatorTest extends BaseUnitTest {

    @Test
    public void calculateNextRetryTime() {

        int interval = 3;
        String intervalExpression = String.valueOf(interval);

        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        Date actual = RetryIntervalCalculator.calculateNextRetryTime(null,  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat), DateUtil.dateFormat(new Date(), dateFormat));

        actual = RetryIntervalCalculator.calculateNextRetryTime("",  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat), DateUtil.dateFormat(new Date(), dateFormat));

        actual = RetryIntervalCalculator.calculateNextRetryTime(intervalExpression,  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, 1 * interval), dateFormat));

        actual = RetryIntervalCalculator.calculateNextRetryTime(intervalExpression,  2);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, 1 * interval), dateFormat));

        actual = RetryIntervalCalculator.calculateNextRetryTime(intervalExpression,  3);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, 1 * interval), dateFormat));

        int multipliper = 2;
        intervalExpression = String.format("%s,%s", interval, multipliper);
        actual = RetryIntervalCalculator.calculateNextRetryTime(intervalExpression,  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, interval * 1 * multipliper), dateFormat));

        actual = RetryIntervalCalculator.calculateNextRetryTime(intervalExpression,  2);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, interval * 2 * multipliper), dateFormat));

        actual = RetryIntervalCalculator.calculateNextRetryTime(intervalExpression,  3);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, interval * 3 * multipliper), dateFormat));
    }
}
