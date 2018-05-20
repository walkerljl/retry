package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * StringUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class StringUtilTest {

    @Test
    public void isEmpty() {

        Assert.assertTrue(StringUtil.isEmpty(null));
        Assert.assertTrue(StringUtil.isEmpty(""));
        Assert.assertTrue(StringUtil.isEmpty("   "));

        Assert.assertFalse(StringUtil.isEmpty("string"));
        Assert.assertFalse(StringUtil.isEmpty(" string "));
    }

    @Test
    public void isNotEmpty() {

        Assert.assertFalse(StringUtil.isNotEmpty(null));
        Assert.assertFalse(StringUtil.isNotEmpty(""));
        Assert.assertFalse(StringUtil.isNotEmpty("   "));

        Assert.assertTrue(StringUtil.isNotEmpty("string"));
        Assert.assertTrue(StringUtil.isNotEmpty(" string "));
    }
}
