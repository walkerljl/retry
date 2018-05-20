package org.walkerljl.retry.model.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RetryPriorityEnumTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryPriorityEnumTest {

    @Test
    public void test() {

        Assert.assertEquals(RetryPriorityEnum.HIGH.getCode(), "1");
        Assert.assertEquals(RetryPriorityEnum.HIGH.getDescription(), "高");

        Assert.assertEquals(RetryPriorityEnum.NORMAL.getCode(), "2");
        Assert.assertEquals(RetryPriorityEnum.NORMAL.getDescription(), "中");

        Assert.assertEquals(RetryPriorityEnum.LOW.getCode(), "3");
        Assert.assertEquals(RetryPriorityEnum.LOW.getDescription(), "低");
    }
}
