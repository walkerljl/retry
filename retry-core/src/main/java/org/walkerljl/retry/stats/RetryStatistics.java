package org.walkerljl.retry.stats;

/**
 * RetryStatistics
 *
 * @author xingxun
 */
public interface RetryStatistics {

    String getName();

    int getRunningCount();

    int getCompletedCount();

    int getErrorCount();

    int getAbortCount();
}