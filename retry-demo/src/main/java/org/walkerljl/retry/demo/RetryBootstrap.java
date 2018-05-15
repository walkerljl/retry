package org.walkerljl.retry.demo;

import java.util.concurrent.CountDownLatch;

import org.walkerljl.retry.RetryServer;

/**
 * RetryBootstrap
 *
 * @author xingxun
 */
public class RetryBootstrap {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        RetryServer retryServer = new DefaultRetryServer();
        retryServer.init();
        retryServer.start();

        countDownLatch.await();
    }
}