package org.walkerljl.retry.demo;

import org.walkerljl.retry.RetryServer;

import java.util.concurrent.CountDownLatch;

/**
 * RetryBootstrap
 *
 * @author xingxun
 */
public class RetryBootstrap {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        RetryServer retryServer = new DefaultRetryServer();
        retryServer.start();

        retryServer.restart();

        countDownLatch.await();
    }
}