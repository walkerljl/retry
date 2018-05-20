package org.walkerljl.retry.exception;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RetryExceptionTest
 *
 * @author xingxun
 */
public class RetryExceptionTest {

    @Test
    public void test() {

        ErrorCode expectedErrorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return "code";
            }

            @Override
            public String getDescription() {
                return "description";
            }
        };

        String expectedErrorMsg = "errorMsg";
        RetryException actual = new RetryException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new RetryException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new RetryException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new RetryException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new RetryException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new RetryException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new RetryException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new RetryException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);
    }

}