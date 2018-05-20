package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * CollectionUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class CollectionUtilTest {

    @Test
    public void isEmpty() {

        Assert.assertTrue(CollectionUtil.isEmpty(null));

        Assert.assertTrue(CollectionUtil.isEmpty(Arrays.asList()));
    }
}
