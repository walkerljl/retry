package org.walkerljl.retry.standard.machine.exception;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.exception.ErrorCode;

/**
 * CannotStopMachineExceptionTest
 *
 * @author xingxun
 * @Date 2018/5/18
 */
public class CannotStopMachineExceptionTest {

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
        CannotStopMachineException actual = new CannotStopMachineException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new CannotStopMachineException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new CannotStopMachineException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotStopMachineException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new CannotStopMachineException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new CannotStopMachineException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotStopMachineException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new CannotStopMachineException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

    }
}
