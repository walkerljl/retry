package org.walkerljl.retry.impl.executor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.executor.impl.DefaultRetryJobExecutor;

/**
 * 重试任务执行器仓库
 *
 * @author xingxun
 */
public class RetryJobExecutorRepository {

    private final Map<String, RetryJobExecutor> REPOSITROY = new HashMap<String, RetryJobExecutor>(3);

    private final ReadWriteLock lock      = new ReentrantReadWriteLock();
    private final Lock          readLock  = lock.readLock();
    private final Lock          writeLock = lock.writeLock();

    /**
     * 私有构造函数
     */
    private RetryJobExecutorRepository() {}

    /**
     * 获取实例
     *
     * @return
     */
    public static RetryJobExecutorRepository getInstance() {
        return RetryJobExecutorRepositoryHolder.instance;
    }

    /**
     * 查找重试执行器
     *
     * @param retryContext 重试上下文
     * @return
     */
    public RetryJobExecutor lookup(RetryContext retryContext) {
        RetryJobExecutor retryJobExecutor = null;
        String executorId = String.valueOf(retryContext.getAttribute(RetryContext.EXECUTOR_ID));

        readLock.lock();
        try {
            retryJobExecutor = REPOSITROY.get(executorId);
            if (retryJobExecutor == null) {
                readLock.unlock();
                writeLock.lock();
                try {
                    retryJobExecutor = REPOSITROY.get(executorId);
                    if (retryJobExecutor == null) {
                        RetryJobExecutorConfig retryJobExecutorConfig = (RetryJobExecutorConfig)
                                retryContext.getAttribute(RetryContext.EXECUTOR_CONFIG);
                        retryJobExecutor = new DefaultRetryJobExecutor(retryJobExecutorConfig);
                        if (retryJobExecutor != null) {
                            retryJobExecutor.start();
                            REPOSITROY.put(executorId, retryJobExecutor);
                        }
                    }
                } finally {
                    writeLock.unlock();
                    readLock.lock();
                }
            }
        } finally {
            readLock.unlock();
        }
        return retryJobExecutor;
    }

    /**
     * 单列容器
     */
    private static class RetryJobExecutorRepositoryHolder {
        private static RetryJobExecutorRepository instance = new RetryJobExecutorRepository();
    }
}