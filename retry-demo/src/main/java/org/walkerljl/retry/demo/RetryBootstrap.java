package org.walkerljl.retry.demo;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RetryBootstrap
 *
 * @author xingxun
 */
public class RetryBootstrap {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        countDownLatch.await();
    }
}