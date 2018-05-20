package org.walkerljl.retry.exception;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;

/**
 * RetryErrorCodeTest
 * 
 * @author xingxun
 */
public class RetryErrorCodeTest extends BaseUnitTest {

    @Test
    public void test() {

        Assert.assertEquals(RetryErrorCode.UNKNOWN.getCode(), "unknown");
        Assert.assertEquals(RetryErrorCode.UNKNOWN.getDescription(), "网络繁忙，请稍后再试");

        Assert.assertEquals(RetryErrorCode.INVALID_PARAM.getCode(), "invalid_param");
        Assert.assertEquals(RetryErrorCode.INVALID_PARAM.getDescription(), "无效的参数");

    }
}