package org.walkerljl.retry;

import org.testng.annotations.Test;
import org.walkerljl.retry.abstracts.AbstractRetryServer;
import org.walkerljl.retry.impl.RetryJobLoader;

/**
 * RetryTest
 *
 * @author xingxun
 */
public class RetryTest extends BaseUnitTest {

    @Test
    public void test() throws InterruptedException {

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