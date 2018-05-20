package org.walkerljl.retry.impl.executor.impl;

import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.RunnableRetryJob;
import org.walkerljl.retry.impl.executor.RetryJobExecutor;
import org.walkerljl.retry.impl.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.impl.util.NamedThreadFactory;
import org.walkerljl.retry.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认的重试任务执行器
 *
 * @author xingxun
 */
public class DefaultRetryJobExecutor extends AbstractMachine implements RetryJobExecutor {

    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private ThreadPoolExecutor threadPoolExecutor;
    private RetryJobExecutorConfig retryJobExecutorConfig;

    /**
     * 构造函数
     *
     * @param retryJobExecutorConfig 重试任务执行器配置
     */
    public DefaultRetryJobExecutor(RetryJobExecutorConfig retryJobExecutorConfig) {
        this.retryJobExecutorConfig = retryJobExecutorConfig;
        threadPoolExecutor = new ThreadPoolExecutor(retryJobExecutorConfig.getCorePoolSize(), retryJobExecutorConfig.getMaxPoolSize(),
                retryJobExecutorConfig.getKeepAliveTime(), KEEP_ALIVE_TIME_UNIT,
                new ArrayBlockingQueue<>(retryJobExecutorConfig.getWorkQueueSize()),
                new NamedThreadFactory(retryJobExecutorConfig.getId()));
    }

    @Override
    public void execute(RunnableRetryJob runnableRetryJob) {
        if (isRunning()) {
            threadPoolExecutor.execute(runnableRetryJob);
        }
    }

    @Override
    public String getId() {
        return retryJobExecutorConfig.getId();
    }

    @Override
    public String getGroup() {
        return RetryConstants.COMPONENT_IDENTIFIER_JOB_EXECUTOR;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }
}