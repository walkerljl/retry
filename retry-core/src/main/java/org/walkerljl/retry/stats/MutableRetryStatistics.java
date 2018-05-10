package org.walkerljl.retry.stats;

/**
 *
 * @author xingxun
 */
public interface MutableRetryStatistics extends RetryStatistics {

    void incrementRunningCount();

    void incrementCompletedCount();

    void incrementErrorCount();

    void incrementAbortCount();
}