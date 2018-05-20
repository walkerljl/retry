package org.walkerljl.retry.standard.resource.exception;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.exception.ErrorCode;

/**
 * ResourceExceptionTest
 *
 * @author xingxun
 * @Date 2018/5/18
 */
public class ResourceExceptionTest {

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
        ResourceException actual = new ResourceException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new ResourceException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new ResourceException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new ResourceException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new ResourceException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new ResourceException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new ResourceException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new ResourceException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

    }
}
