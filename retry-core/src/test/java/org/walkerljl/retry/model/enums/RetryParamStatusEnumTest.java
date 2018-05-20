package org.walkerljl.retry.model.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RetryParamStatusEnumTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryParamStatusEnumTest {

    @Test
    public void test() {

        Assert.assertEquals(RetryParamStatusEnum.NORMAL.getCode(), "normal");
        Assert.assertEquals(RetryParamStatusEnum.NORMAL.getDescription(), "正常");

        Assert.assertEquals(RetryParamStatusEnum.DELETED.getCode(), "deleted");
        Assert.assertEquals(RetryParamStatusEnum.DELETED.getDescription(), "已删除");
    }
}
