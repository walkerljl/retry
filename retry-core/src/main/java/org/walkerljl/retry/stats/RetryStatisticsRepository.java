/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package org.walkerljl.retry.stats;

/**
 *
 * @author xingxun
 */
public interface RetryStatisticsRepository {

    RetryStatistics findOne(String name);

    Iterable<RetryStatistics> findAll();

    void addRunning(String name);

    void addError(String name);

    void addCompleted(String name);

    void addAbort(String name);
}