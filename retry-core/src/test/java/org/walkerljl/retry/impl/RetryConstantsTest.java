package org.walkerljl.retry.impl;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;

/**
 * RetryConstantsTest
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public class RetryConstantsTest extends BaseUnitTest {

    @Test
    public void test() {

        Assert.assertEquals(RetryConstants.COMPONENT_IDENTIFIER_JOB_DISPATCHER, "RetryJobDispatcher");
        Assert.assertEquals(RetryConstants.COMPONENT_IDENTIFIER_JOB_EXECUTOR, "RetryJobExecutor");
        Assert.assertEquals(RetryConstants.COMPONENT_IDENTIFIER_JOB_LOADER, "RetryJobLoder");
        Assert.assertEquals(RetryConstants.COMPONENT_IDENTIFIER_RETRY_BROKER, "RetryBroker");
        Assert.assertEquals(RetryConstants.COMPONENT_IDENTIFIER_RETRY_SERVER, "RetryServer");
        Assert.assertEquals(RetryConstants.DEFAULT_COMPONENT_ID_RETRY_BROKER, "default");
        Assert.assertEquals(RetryConstants.DEFAULT_COMPONENT_ID_RETRY_SERVER, "default");

    }
}
