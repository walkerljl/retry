package org.walkerljl.retry.impl.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * NamedThreadFactoryTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class NamedThreadFactoryTest {

    /** 分隔符*/
    private static final String SEPARATOR = "-";

    @Test
    public void test() {

        NamedThreadFactory namedThreadFactory = new NamedThreadFactory();

        Thread actual = namedThreadFactory.newThread(new DummyRunnable());
        Assert.assertTrue(actual.getName().startsWith("pool"));
        Assert.assertFalse(actual.isDaemon());
        Assert.assertEquals(String.valueOf(actual.getName().split(SEPARATOR)[3]), "1");
        actual = namedThreadFactory.newThread(new DummyRunnable());
        Assert.assertFalse(actual.isDaemon());
        Assert.assertEquals(String.valueOf(actual.getName().split(SEPARATOR)[3]), "2");

        namedThreadFactory = new NamedThreadFactory("retry");
        actual = namedThreadFactory.newThread(new DummyRunnable());
        Assert.assertTrue(actual.getName().startsWith("retry"));
        Assert.assertFalse(actual.isDaemon());
        Assert.assertEquals(String.valueOf(actual.getName().split(SEPARATOR)[2]), "1");
        actual = namedThreadFactory.newThread(new DummyRunnable());
        Assert.assertFalse(actual.isDaemon());
        Assert.assertEquals(String.valueOf(actual.getName().split(SEPARATOR)[2]), "2");

        namedThreadFactory = new NamedThreadFactory("retry", true);
        actual = namedThreadFactory.newThread(new DummyRunnable());
        Assert.assertTrue(actual.getName().startsWith("retry"));
        Assert.assertTrue(actual.isDaemon());
        Assert.assertEquals(String.valueOf(actual.getName().split(SEPARATOR)[2]), "1");
        actual = namedThreadFactory.newThread(new DummyRunnable());
        Assert.assertTrue(actual.isDaemon());
        Assert.assertEquals(String.valueOf(actual.getName().split(SEPARATOR)[2]), "2");
    }
}

class DummyRunnable implements Runnable {

    @Override
    public void run() {

    }
}
