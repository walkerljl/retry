package org.walkerljl.retry.impl.executor.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.walkerljl.retry.impl.executor.RetryJobExecutor;
import org.walkerljl.retry.impl.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.impl.RunnableRetryJob;
import org.walkerljl.retry.impl.util.NamedThreadFactory;

/**
 * 默认的重试任务执行器
 *
 * @author xingxun
 */
public class DefaultRetryJobExecutor implements RetryJobExecutor {

    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 构造函数
     *
     * @param retryJobExecutorConfig 重试任务执行器配置
     */
    public DefaultRetryJobExecutor(RetryJobExecutorConfig retryJobExecutorConfig) {
        threadPoolExecutor = new ThreadPoolExecutor(retryJobExecutorConfig.getCorePoolSize(), retryJobExecutorConfig.getMaxPoolSize(),
                retryJobExecutorConfig.getKeepAliveTime(), KEEP_ALIVE_TIME_UNIT,
                new ArrayBlockingQueue<>(retryJobExecutorConfig.getWorkQueueSize()),
                new NamedThreadFactory(retryJobExecutorConfig.getId()));
    }

    @Override
    public void execute(RunnableRetryJob runnableRetryJob) {
        threadPoolExecutor.execute(runnableRetryJob);
    }
}