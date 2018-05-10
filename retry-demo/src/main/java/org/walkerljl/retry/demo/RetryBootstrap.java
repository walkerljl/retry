package org.walkerljl.retry.demo;

import java.util.concurrent.CountDownLatch;

import org.walkerljl.retry.demo.impl.defaults.DefaultLoggerRepository;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.log.logger.LoggerRepository;

/**
 * RetryBootstrap
 *
 * @author xingxun
 */
public class RetryBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryBootstrap.class);

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {

            LoggerRepository loggerRepository = new DefaultLoggerRepository();
            LoggerFactory.bindLoggerRepository(loggerRepository);

            new RetryServer().start();

            countDownLatch.await();
        } catch (Exception e) {
            countDownLatch.countDown();
            LOGGER.error(e);
        }
    }
}