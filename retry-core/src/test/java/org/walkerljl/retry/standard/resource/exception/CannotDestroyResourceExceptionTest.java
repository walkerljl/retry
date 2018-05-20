package org.walkerljl.retry.standard.resource.exception;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.exception.ErrorCode;

/**
 * CannotDestroyResourceExceptionTest
 *
 * @author xingxun
 * @Date 2018/5/18
 */
public class CannotDestroyResourceExceptionTest {

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
        CannotDestroyResourceException actual = new CannotDestroyResourceException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new CannotDestroyResourceException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new CannotDestroyResourceException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotDestroyResourceException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new CannotDestroyResourceException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new CannotDestroyResourceException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotDestroyResourceException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotDestroyResourceException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

    }
}
