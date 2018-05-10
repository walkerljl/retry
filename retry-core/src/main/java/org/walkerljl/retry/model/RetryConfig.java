package org.walkerljl.retry.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.walkerljl.retry.alarm.impl.LoggerAlarmListener;
import org.walkerljl.retry.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.stats.RetryStatisticsListener;
import org.walkerljl.retry.stats.impl.DefaultRetryStatisticsRepository;
import org.walkerljl.retry.util.AssertUtil;

/**
 * Retry configuration
 *
 * @author xingxun
 */
public class RetryConfig extends BaseEntity {

    private static final long serialVersionUID = -4088342977048677947L;

    /** Interval of job load*/
    private long jobLoadInterval        = 5;
    /** Max job amount percent load*/
    private int  maxJobAmountPerLoad    = 1000;
    /** Job load page size*/
    private int  jobLoadPageSize        = 200;
    /** Job oad begin page number*/
    private int  jobLoadBeginPageNumber = 1;
    /** Timeout of job retry*/
    private long jobRetryTimeout        = 300;

    private Map<String, RetryJobExecutorConfig> retryJobExecutorConfigMap;

    private List<RetryListener> listeners = new ArrayList<RetryListener>(2);

    public RetryConfig() {
        registerListener(new RetryStatisticsListener(new DefaultRetryStatisticsRepository()));
        registerListener(new LoggerAlarmListener());
    }

    public void registerListener(RetryListener retryListener) {
        AssertUtil.assertTrue(retryListener != null, "Retry listener can not be null.");
        listeners.add(retryListener);
    }

    public List<RetryListener> listListeners() {
        return Collections.unmodifiableList(listeners);
    }

    /**
     * Getter method for property <tt>jobLoadInterval</tt>.
     *
     * @return property value of jobLoadInterval
     */
    public long getJobLoadInterval() {
        return jobLoadInterval;
    }

    /**
     * Setter method for property <tt>jobLoadInterval</tt>.
     *
     * @param jobLoadInterval  value to be assigned to property jobLoadInterval
     */
    public void setJobLoadInterval(long jobLoadInterval) {
        this.jobLoadInterval = jobLoadInterval;
    }

    /**
     * Getter method for property <tt>maxJobAmountPerLoad</tt>.
     *
     * @return property value of maxJobAmountPerLoad
     */
    public int getMaxJobAmountPerLoad() {
        return maxJobAmountPerLoad;
    }

    /**
     * Setter method for property <tt>maxJobAmountPerLoad</tt>.
     *
     * @param maxJobAmountPerLoad  value to be assigned to property maxJobAmountPerLoad
     */
    public void setMaxJobAmountPerLoad(int maxJobAmountPerLoad) {
        this.maxJobAmountPerLoad = maxJobAmountPerLoad;
    }

    /**
     * Getter method for property <tt>jobLoadPageSize</tt>.
     *
     * @return property value of jobLoadPageSize
     */
    public int getJobLoadPageSize() {
        return jobLoadPageSize;
    }

    /**
     * Setter method for property <tt>jobLoadPageSize</tt>.
     *
     * @param jobLoadPageSize  value to be assigned to property jobLoadPageSize
     */
    public void setJobLoadPageSize(int jobLoadPageSize) {
        this.jobLoadPageSize = jobLoadPageSize;
    }

    /**
     * Getter method for property <tt>jobLoadBeginPageNumber</tt>.
     *
     * @return property value of jobLoadBeginPageNumber
     */
    public int getJobLoadBeginPageNumber() {
        return jobLoadBeginPageNumber;
    }

    /**
     * Setter method for property <tt>jobLoadBeginPageNumber</tt>.
     *
     * @param jobLoadBeginPageNumber  value to be assigned to property jobLoadBeginPageNumber
     */
    public void setJobLoadBeginPageNumber(int jobLoadBeginPageNumber) {
        this.jobLoadBeginPageNumber = jobLoadBeginPageNumber;
    }

    /**
     * Getter method for property <tt>jobRetryTimeout</tt>.
     *
     * @return property value of jobRetryTimeout
     */
    public long getJobRetryTimeout() {
        return jobRetryTimeout;
    }

    /**
     * Setter method for property <tt>jobRetryTimeout</tt>.
     *
     * @param jobRetryTimeout  value to be assigned to property jobRetryTimeout
     */
    public void setJobRetryTimeout(long jobRetryTimeout) {
        this.jobRetryTimeout = jobRetryTimeout;
    }

    /**
     * Getter method for property <tt>retryJobExecutorConfigMap</tt>.
     *
     * @return property value of retryJobExecutorConfigMap
     */
    public Map<String, RetryJobExecutorConfig> getRetryJobExecutorConfigMap() {
        return retryJobExecutorConfigMap;
    }

    /**
     * Setter method for property <tt>retryJobExecutorConfigMap</tt>.
     *
     * @param retryJobExecutorConfigMap  value to be assigned to property retryJobExecutorConfigMap
     */
    public void setRetryJobExecutorConfigMap(
            Map<String, RetryJobExecutorConfig> retryJobExecutorConfigMap) {
        this.retryJobExecutorConfigMap = retryJobExecutorConfigMap;
    }

    /**
     * Getter method for property <tt>listeners</tt>.
     *
     * @return property value of listeners
     */
    public List<RetryListener> getListeners() {
        return listeners;
    }

    /**
     * Setter method for property <tt>listeners</tt>.
     *
     * @param listeners  value to be assigned to property listeners
     */
    public void setListeners(List<RetryListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}