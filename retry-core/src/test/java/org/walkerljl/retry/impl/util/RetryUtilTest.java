package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.listener.impl.DummyRetryListener;
import org.walkerljl.retry.model.RetryConfig;

import java.util.List;

/**
 * RetryUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryUtilTest {

    @Test
    public void buildIdentifier() {

        String actual = RetryUtil.buildIdentifier("1", "2");
        Assert.assertEquals(actual, "1:2");
    }

    @Test
    public void getRetryConfig() {

        RetryConfig actual = RetryUtil.getRetryConfig(null);
        Assert.assertNull(actual);

        RetryContext retryContext = new RetryContext();
        RetryConfig expected = new RetryConfig();
        retryContext.setAttribute(RetryContext.RETRY_CONFIG, expected);
        actual = RetryUtil.getRetryConfig(retryContext);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getListeners() {

        List<RetryListener> actual = RetryUtil.getListeners(null);
        Assert.assertTrue(CollectionUtil.isEmpty(actual));

        RetryConfig retryConfig = new RetryConfig();
        RetryListener expected = new DummyRetryListener();
        retryConfig.registerListener(expected);

        actual = RetryUtil.getListeners(retryConfig);
        Assert.assertEquals(actual.get(3), expected);
    }
}
