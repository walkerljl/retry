package org.walkerljl.retry;

import java.util.List;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.ReleaseRetryJobParam;

/**
 * Retry service
 *
 * @author xingxun
 */
public interface RetryService {

    RetryJob getRetryJob(String bizType, String bizId);

    int lockRetryJob(LockRetryJobParam lockRetryJobParam);

    void unlockRetryJob(ReleaseRetryJobParam releaseRetryJobParam);

    void recordRetryLog(RetryLog retryLog);

    List<RetryJob> listUnprocessRetryJobsByPage(long jobLoadInterval, int currentPage, int pageSize);

    List<RetryJob> listFailureRetryJobsByPage(long jobLoadInterval, int currentPage, int pageSize);

    List<RetryJob> listTimeoutRetryJobsByPage(long jobLoadInterval, long retryTimeout, int currentPage, int pageSize);
}