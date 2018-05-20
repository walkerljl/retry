package org.walkerljl.retry.abstracts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.RetryJobDispatcher;
import org.walkerljl.retry.RetryServer;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.RetryJobLoader;

/**
 * AbstractRetryServerTest
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public class AbstractRetryServerTest extends BaseUnitTest {

    @Test
    public void test() {

        RetryServer retryServer = new DefaultRetryServer();
        Assert.assertEquals(retryServer.getId(), RetryConstants.DEFAULT_COMPONENT_ID_RETRY_SERVER);
        Assert.assertEquals(retryServer.getGroup(), RetryConstants.COMPONENT_IDENTIFIER_RETRY_SERVER);

        retryServer.start();
        Assert.assertTrue(retryServer.isRunning());

        retryServer.stop();
        Assert.assertFalse(retryServer.isRunning());

    }
}

class DefaultRetryServer extends AbstractRetryServer implements RetryServer {

    @Override
    public RetryJobLoader getRetryJobLoader() {
        return null;
    }

    @Override
    public RetryJobDispatcher getRetryJobDispatcher() {
        return null;
    }
}
