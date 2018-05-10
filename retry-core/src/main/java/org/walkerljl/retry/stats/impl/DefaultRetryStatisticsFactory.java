package org.walkerljl.retry.stats.impl;

import org.walkerljl.retry.stats.MutableRetryStatistics;
import org.walkerljl.retry.stats.RetryStatisticsFactory;

/**
 * DefaultRetryStatisticsFactory
 *
 * @author xingxun
 */
public class DefaultRetryStatisticsFactory implements RetryStatisticsFactory {

    @Override
    public MutableRetryStatistics create(String name) {
        return new DefaultRetryStatistics(name);
    }
}