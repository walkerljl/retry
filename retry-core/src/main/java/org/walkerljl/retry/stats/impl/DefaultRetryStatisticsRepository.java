package org.walkerljl.retry.stats.impl;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.walkerljl.retry.stats.MutableRetryStatistics;
import org.walkerljl.retry.stats.RetryStatistics;
import org.walkerljl.retry.stats.RetryStatisticsFactory;
import org.walkerljl.retry.stats.RetryStatisticsRepository;

/**
 * DefaultRetryStatisticsRepository
 *
 * @author xingxun
 */
public class DefaultRetryStatisticsRepository implements RetryStatisticsRepository {

    private ConcurrentMap<String, MutableRetryStatistics> repository = new ConcurrentHashMap<String, MutableRetryStatistics>();
    private RetryStatisticsFactory                        factory    = new DefaultRetryStatisticsFactory();

    @Override
    public RetryStatistics findOne(String name) {
        return repository.get(name);
    }

    @Override
    public Iterable<RetryStatistics> findAll() {
        return new ArrayList<RetryStatistics>(repository.values());
    }

    @Override
    public void addRunning(String name) {
        getStatistics(name).incrementRunningCount();
    }

    @Override
    public void addError(String name) {
        getStatistics(name).incrementErrorCount();
    }

    @Override
    public void addCompleted(String name) {
        getStatistics(name).incrementCompletedCount();
    }

    @Override
    public void addAbort(String name) {
        getStatistics(name).incrementAbortCount();
    }

    private MutableRetryStatistics getStatistics(String name) {
        MutableRetryStatistics stats;
        if (!repository.containsKey(name)) {
            repository.putIfAbsent(name, factory.create(name));
        }
        stats = repository.get(name);
        return stats;
    }

    public void setRetryStatisticsFactory(RetryStatisticsFactory factory) {
        this.factory = factory;
    }
}