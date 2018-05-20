package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ThrowableUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class ThrowableUtilTest {

    @Test
    public void getMessage() {

        String actual = ThrowableUtil.getMessage(null);
        Assert.assertEquals(actual, null);

        String expected = "errorMsg";
        Throwable throwable = new RuntimeException(expected);
        actual = ThrowableUtil.getMessage(throwable);
        Assert.assertEquals(actual, expected);
    }
}
