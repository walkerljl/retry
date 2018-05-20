package org.walkerljl.retry.impl.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NamedThreadFactory
 *
 * @author xingxun
 */
public class NamedThreadFactory implements ThreadFactory {

    /** 分隔符*/
    private static final String SEPARATOR = "-";
    /** 默认的线程池前缀*/
    private static final String DEFAULT_PREFIX = "pool";
    /** 关键字：thread*/
    private static final String KEY_THREAD = "thread";
    /** 线程池序列号*/
    private static final AtomicInteger POOL_SEQ   = new AtomicInteger(1);
    /** 线程数*/
    private final        AtomicInteger threadNum = new AtomicInteger(1);
    /** 线程池名称前缀*/
    private final String      prefix;
    /** 是否守护线程*/
    private final boolean     daemon;
    /** 线程组*/
    private final ThreadGroup group;

    /**
     * 构造函数
     */
    public NamedThreadFactory() {
        this(String.format("%s%s%s", DEFAULT_PREFIX, SEPARATOR, POOL_SEQ.getAndIncrement()), false);
    }

    /**
     * 构造函数
     *
     * @param prefix 线程池名称前缀
     */
    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    /**
     * 构造函数
     *
     * @param prefix 前缀
     * @param isDaemon 是否守护线程，true：是，false：否
     */
    public NamedThreadFactory(String prefix, boolean isDaemon) {
        this.prefix = String.format("%s%s%s%s", prefix, SEPARATOR, KEY_THREAD, SEPARATOR);
        daemon = isDaemon;
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager == null) ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = prefix + threadNum.getAndIncrement();
        Thread thread = new Thread(group, runnable, name, 0);
        thread.setDaemon(daemon);
        return thread;
    }
}