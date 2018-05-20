package org.walkerljl.retry.model.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RetryJobStatusEnumTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryJobStatusEnumTest {

    @Test
    public void test() {

        Assert.assertEquals(RetryJobStatusEnum.UNPROCESS.getCode(), "unprocess");
        Assert.assertEquals(RetryJobStatusEnum.UNPROCESS.getDescription(), "未处理");

        Assert.assertEquals(RetryJobStatusEnum.PROCESSING.getCode(), "processing");
        Assert.assertEquals(RetryJobStatusEnum.PROCESSING.getDescription(), "处理中");

        Assert.assertEquals(RetryJobStatusEnum.PROCESSED.getCode(), "processed");
        Assert.assertEquals(RetryJobStatusEnum.PROCESSED.getDescription(), "已处理");

        Assert.assertEquals(RetryJobStatusEnum.FAILURE.getCode(), "failure");
        Assert.assertEquals(RetryJobStatusEnum.FAILURE.getDescription(), "处理失败");

        Assert.assertEquals(RetryJobStatusEnum.DELETED.getCode(), "deleted");
        Assert.assertEquals(RetryJobStatusEnum.DELETED.getDescription(), "已删除");
    }
}
