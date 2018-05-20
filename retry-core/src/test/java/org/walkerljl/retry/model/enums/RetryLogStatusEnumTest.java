package org.walkerljl.retry.model.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RetryLogStatusEnumTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryLogStatusEnumTest {

    @Test
    public void test() {

        Assert.assertEquals(RetryLogStatusEnum.SUCCESS.getCode(), "success");
        Assert.assertEquals(RetryLogStatusEnum.SUCCESS.getDescription(), "成功");

        Assert.assertEquals(RetryLogStatusEnum.FAILURE.getCode(), "failure");
        Assert.assertEquals(RetryLogStatusEnum.FAILURE.getDescription(), "失败");

        Assert.assertEquals(RetryLogStatusEnum.DELETED.getCode(), "deleted");
        Assert.assertEquals(RetryLogStatusEnum.DELETED.getDescription(), "已删除");
    }
}
