package org.walkerljl.retry.executor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.walkerljl.retry.executor.impl.DefaultRetryJobExecutor;
import org.walkerljl.retry.support.RetryContext;

/**
 * RetryJobExecutorRepository
 *
 * @author xingxun
 */
public class RetryJobExecutorRepository {

    private final Map<String, RetryJobExecutor> REPOSITROY = new HashMap<String, RetryJobExecutor>();

    private final ReadWriteLock lock      = new ReentrantReadWriteLock();
    private final Lock          readLock  = lock.readLock();
    private final Lock          writeLock = lock.writeLock();

    private RetryJobExecutorRepository() {}

    public static RetryJobExecutorRepository getInstance() {
        return RetryJobExecutorRepositoryHolder.instance;
    }

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
                    if (retryJobExecutor == null) {
                        RetryJobExecutorConfig retryJobExecutorConfig = (RetryJobExecutorConfig)
                                retryContext.getAttribute(RetryContext.EXECUTOR_CONFIG);
                        retryJobExecutor = new DefaultRetryJobExecutor(retryJobExecutorConfig);
                        if (retryJobExecutor != null) {
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

    private static class RetryJobExecutorRepositoryHolder {
        private static RetryJobExecutorRepository instance = new RetryJobExecutorRepository();
    }
}