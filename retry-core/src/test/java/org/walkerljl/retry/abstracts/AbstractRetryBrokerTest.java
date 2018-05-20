package org.walkerljl.retry.abstracts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.model.RetryJob;

/**
 * AbstractRetryBrokerTest
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public class AbstractRetryBrokerTest extends BaseUnitTest {

    @Test
    public void test() {

        RetryBroker retryBroker = new DefaultRetryBroker();
        Assert.assertEquals(retryBroker.getId(), RetryConstants.DEFAULT_COMPONENT_ID_RETRY_BROKER);
        Assert.assertEquals(retryBroker.getGroup(), RetryConstants.COMPONENT_IDENTIFIER_RETRY_BROKER);

        retryBroker.submit(null);
        retryBroker.markRetryJobToCompleted(null);

        retryBroker.start();

        retryBroker.submit(null);
        retryBroker.markRetryJobToCompleted(null);

        retryBroker.stop();
        retryBroker.submit(null);
        retryBroker.markRetryJobToCompleted(null);
    }
}

class DefaultRetryBroker extends AbstractRetryBroker implements RetryBroker {

    @Override
    public String processSubmit(RetryJob retryJob) {
        return null;
    }

    @Override
    public void processMarkRetryJobToCompleted(String retryJobId) {
        Assert.assertNotNull(retryJobId);
    }
}
