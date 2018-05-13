package org.walkerljl.retry.impl.util;

import org.walkerljl.retry.exception.ErrorCode;
import org.walkerljl.retry.exception.RetryErrorCode;
import org.walkerljl.retry.exception.RetryException;

/**
 * AssertUtil
 *
 * @author xingxun
 * @Date 2017/10/22
 */
public class AssertUtil {

    /**
     * Assert expression is true
     *
     * @param expression Expression
     * @param message Error message
     */
    public static void assertTrue(boolean expression, String message) {
        if (expression) {
            return;
        }
        throw new RetryException(message);
    }

    /**
     * Assert expression is true
     *
     * @param expression Expression
     * @param errorCode Error code
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode) {
        if (expression) {
            return;
        }
        throw new RetryException(errorCode);
    }

    /**
     * Assert expression is true
     *
     * @param expression Expression
     * @param errorCode Error code
     * @param message Error message
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode, String message) {
        if (expression) {
            return;
        }
        throw new RetryException(errorCode, message);
    }

    /**
     * Assert parameter
     *
     * @param expression Expression
     * @param paramName Name of parameter
     */
    public static void assertParam(boolean expression, String paramName) {
        assertParam(expression, RetryErrorCode.INVALID_PARAM, paramName);
    }

    /**
     * Assert parameter
     *
     * @param expression Expression
     * @param errorCode Error code
     * @param paramName Name of parameter
     */
    public static void assertParam(boolean expression, ErrorCode errorCode, String paramName) {

        if (!expression) {
            String message = String.format("%s:%s", errorCode.getDescription(), paramName);
            throw new RetryException(errorCode, message);
        }
    }
}
