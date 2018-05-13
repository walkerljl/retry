package org.walkerljl.retry.stats.impl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.BaseEntity;
import org.walkerljl.retry.stats.MutableRetryStatistics;

/**
 * DefaultRetryStatistics
 *
 * @author xingxun
 */
public class DefaultRetryStatistics extends BaseEntity implements MutableRetryStatistics {

    private static final Logger RETRY_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY);
    private static final Logger STATISTICS_LOGGER = LoggerFactory.getLogger(LoggerNames.STATISTICS);

    private String name;
    private AtomicInteger runningCount   = new AtomicInteger();
    private AtomicInteger completedCount = new AtomicInteger();
    private AtomicInteger errorCount     = new AtomicInteger();
    private AtomicInteger abortCount     = new AtomicInteger();

    public DefaultRetryStatistics(String name) {
        this.name = name;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String statisticsLogContent = String.format("[%s]%s,%s,%s,%s.", name,
                            runningCount,
                            completedCount,
                            errorCount,
                            abortCount);
                    if (STATISTICS_LOGGER.isInfoEnabled()) {
                        LoggerUtil.info(STATISTICS_LOGGER, statisticsLogContent);
                    }
                } catch (Throwable e) {
                    LoggerUtil.error(RETRY_LOGGER, e);
                }
            }
        },1000,1000 );
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