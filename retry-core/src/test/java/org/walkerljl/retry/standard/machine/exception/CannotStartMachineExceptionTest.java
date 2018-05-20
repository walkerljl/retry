package org.walkerljl.retry.standard.machine.exception;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.exception.ErrorCode;

/**
 * CannotStartMachineExceptionTest
 *
 * @author xingxun
 * @Date 2018/5/18
 */
public class CannotStartMachineExceptionTest {

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
        CannotStartMachineException actual = new CannotStartMachineException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new CannotStartMachineException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new CannotStartMachineException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotStartMachineException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new CannotStartMachineException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new CannotStartMachineException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotStartMachineException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotStartMachineException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

    }
}
