package org.walkerljl.retry.model;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.listener.impl.DummyRetryListener;

import java.util.Collection;

/**
 * RetryConfigTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryConfigTest extends BaseUnitTest {

    /** 任务加载间隔，单位：秒*/
    private long jobLoadInterval        = 30;
    /** 每次加载的最大任务数*/
    private int  maxJobQuantityPerLoad  = 1000;
    /** 每次分页查询的任务条数*/
    private int  jobLoadPageSize        = 200;
    /** 任务加载起始页码*/
    private int  jobLoadBeginPageNumber = 1;
    /** 任务重试超时时间，单位：秒*/
    private long jobRetryTimeout        = 300;

    @Test
    public void test() {

        RetryConfig actual = new RetryConfig();
        Assert.assertEquals(actual.getJobLoadInterval(), jobLoadInterval);
        Assert.assertEquals(actual.getMaxJobQuantityPerLoad(), maxJobQuantityPerLoad);
        Assert.assertEquals(actual.getJobLoadPageSize(), jobLoadPageSize);
        Assert.assertEquals(actual.getJobLoadBeginPageNumber(), jobLoadBeginPageNumber);
        Assert.assertEquals(actual.getJobRetryTimeout(), jobRetryTimeout);

        RetryListener retryListener = new DummyRetryListener();
        actual.registerListener(retryListener);

        boolean actualFlag = false;
        Collection<RetryListener> allListeners = actual.listListeners();
        try {
           allListeners.add(retryListener);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

    }
}
