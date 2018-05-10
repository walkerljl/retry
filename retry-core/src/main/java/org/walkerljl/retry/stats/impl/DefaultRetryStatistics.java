package org.walkerljl.retry.stats.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.walkerljl.retry.model.BaseEntity;
import org.walkerljl.retry.stats.MutableRetryStatistics;

/**
 * DefaultRetryStatistics
 *
 * @author xingxun
 */
public class DefaultRetryStatistics extends BaseEntity implements MutableRetryStatistics {

    private String name;
    private AtomicInteger runningCount   = new AtomicInteger();
    private AtomicInteger completedCount = new AtomicInteger();
    private AtomicInteger errorCount     = new AtomicInteger();
    private AtomicInteger abortCount     = new AtomicInteger();

    public DefaultRetryStatistics(String name) {
        this.name = name;
    }

    @Override
    public int getCompletedCount() {
        return completedCount.get();
    }

    @Override
    public int getRunningCount() {
        return runningCount.get();
    }

    @Override
    public int getErrorCount() {
        return errorCount.get();
    }

    @Override
    public int getAbortCount() {
        return abortCount.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void incrementRunningCount() {
        this.runningCount.incrementAndGet();
    }

    @Override
    public void incrementCompletedCount() {
        this.completedCount.incrementAndGet();
    }

    @Override
    public void incrementErrorCount() {
        this.errorCount.incrementAndGet();
    }

    @Override
    public void incrementAbortCount() {
        this.abortCount.incrementAndGet();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}