package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ArrayUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class ArrayUtilTest {

    @Test
    public void isEmpty() {

        boolean actual = ArrayUtil.isEmpty(null);
        Assert.assertTrue(actual);

        actual = ArrayUtil.isEmpty(new String[]{});
        Assert.assertTrue(actual);
    }

    @Test
    public void concat() {

        String separator = ",";
        String actual = ArrayUtil.concat(separator, new String[]{});
        Assert.assertEquals(actual, "");

        actual = ArrayUtil.concat(separator, new Integer[]{1, 2, 3});
        Assert.assertEquals(actual, "1,2,3");
    }
}
