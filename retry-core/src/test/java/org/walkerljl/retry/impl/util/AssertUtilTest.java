package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.exception.ErrorCode;
import org.walkerljl.retry.exception.RetryErrorCode;
import org.walkerljl.retry.exception.RetryException;

/**
 * AssertUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class AssertUtilTest {

    @Test
    public void assertTrue() {
        String actualErrorMsg = "actualErrorMsg";
        AssertUtil.assertTrue(true, actualErrorMsg);
        boolean result = false;
        try {
            AssertUtil.assertTrue(false, actualErrorMsg);
        } catch (RetryException e) {
            if (e.getCode() == null && actualErrorMsg.equals(e.getMessage())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
        result = false;

        ErrorCode actualErrorCode = RetryErrorCode.INVALID_PARAM;
        try {
            AssertUtil.assertTrue(false, actualErrorCode);
        } catch (RetryException e) {
            if (e.getCode() == actualErrorCode && actualErrorCode.getDescription().equals(e.getMessage())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
        result = false;

        try {
            AssertUtil.assertTrue(false, actualErrorCode, actualErrorMsg);
        } catch (RetryException e) {
            if (e.getCode() == actualErrorCode && actualErrorMsg.equals(e.getMessage())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void assertParam() {
        String paramName = "paramName";
        AssertUtil.assertParam(true, "paramName");
        boolean result = false;
        try {
            AssertUtil.assertParam(false, paramName);
        } catch (RetryException e) {
            String errorMsg = String.format("%s:%s", RetryErrorCode.INVALID_PARAM.getDescription(), paramName);
            if (e.getCode() == RetryErrorCode.INVALID_PARAM && (errorMsg).equals(e.getMessage())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
        result = false;

        AssertUtil.assertParam(true, RetryErrorCode.UNKNOWN, "paramName");
        try {
            AssertUtil.assertParam(false, RetryErrorCode.UNKNOWN, paramName);
        } catch (RetryException e) {
            if (e.getCode() == RetryErrorCode.UNKNOWN && (String.format("%s:%s", RetryErrorCode.UNKNOWN.getDescription(), paramName)).equals(e.getMessage())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }
}
