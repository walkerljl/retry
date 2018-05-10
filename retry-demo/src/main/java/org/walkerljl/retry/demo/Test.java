package org.walkerljl.retry.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author xingxun
 */
public class Test {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(Integer.MAX_VALUE);
        counter.addAndGet(1);
        System.out.println(counter);
    }
}