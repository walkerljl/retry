package org.walkerljl.retry.stats;

/**
 * RetryStatisticsFactory
 *
 * @author xingxun
 */
public interface RetryStatisticsFactory {

    MutableRetryStatistics create(String name);
}