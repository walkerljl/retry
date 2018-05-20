package org.walkerljl.retry.impl;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.RetryHandler;
import org.walkerljl.retry.exception.RetryException;

import java.util.Collection;

/**
 * RetryJobHandlerRepositoryTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryJobHandlerRepositoryTest {

    @Test
    public void test() {

        boolean actualFlag = false;
        try {
            RetryJobHandlerRepository.lookup(null);
        } catch (RetryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;

        String handlerId = "handlerId";
        RetryHandler actual = RetryJobHandlerRepository.lookup(handlerId);
        Assert.assertNull(actual);

        RetryHandler expected = new RetryHandler() {
            @Override
            public void retry(RetryContext context) {

            }
        };
        RetryJobHandlerRepository.register(handlerId, expected);
        actual = RetryJobHandlerRepository.lookup(handlerId);
        Assert.assertEquals(actual, expected);

        Collection<RetryHandler> allRetryHandlers = RetryJobHandlerRepository.lookupAll();
        Assert.assertEquals(allRetryHandlers.size(), 1);
        Assert.assertEquals(allRetryHandlers.iterator().next(), expected);
        try {
            allRetryHandlers.add(expected);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        RetryJobHandlerRepository.unregister(handlerId);
        actual = RetryJobHandlerRepository.lookup(handlerId);
        Assert.assertNull(actual);
    }
}
