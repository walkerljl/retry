package org.walkerljl.retry.executor.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.walkerljl.retry.executor.RetryJobExecutor;
import org.walkerljl.retry.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.support.RunnableRetryJob;
import org.walkerljl.retry.util.NamedThreadFactory;

/**
 * DefaultRetryJobExecutor
 *
 * @author xingxun
 */
public class DefaultRetryJobExecutor implements RetryJobExecutor {

    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private ThreadPoolExecutor threadPoolExecutor;

    public DefaultRetryJobExecutor(RetryJobExecutorConfig config) {
        threadPoolExecutor = new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaxPoolSize(),
                config.getKeepAliveTime(), KEEP_ALIVE_TIME_UNIT,
                new ArrayBlockingQueue<Runnable>(config.getWorkQueueSize()),
                new NamedThreadFactory(config.getId()));
    }

    @Override
    public void execute(RunnableRetryJob runnableRetryJob) {
        threadPoolExecutor.execute(runnableRetryJob);
    }
}