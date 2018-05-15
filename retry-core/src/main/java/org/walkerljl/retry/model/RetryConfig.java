package org.walkerljl.retry.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.walkerljl.retry.alarm.impl.LoggerAlarmRetryListener;
import org.walkerljl.retry.impl.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.impl.util.AssertUtil;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.listener.impl.RetryJobExecutorListener;
import org.walkerljl.retry.stats.RetryStatisticsListener;
import org.walkerljl.retry.stats.impl.DefaultRetryStatisticsRepository;

/**
 * 重试配置
 *
 * @author xingxun
 */
public class RetryConfig extends BaseEntity {

    private static final long serialVersionUID = -4088342977048677947L;

    /** 任务加载间隔，单位：秒*/
    private long jobLoadInterval        = 30;
    /** 每次加载的最大任务数*/
    private int  maxJobQuantityPerLoad  = 1000;
    /** 每次分页查询的任务条数*/
    private int  jobLoadPageSize        = 200;
    /** 任务加载起始页码*/
    private int  jobLoadBeginPageNumber = 1;
    /** 任务重试超时时间，单位：秒*/
    private long jobRetryTimeout        = 300;

    private Map<String, RetryJobExecutorConfig> retryJobExecutorConfigMap;

    private List<RetryListener> listeners = new ArrayList<RetryListener>(3);

    public RetryConfig() {
        registerListener(new RetryJobExecutorListener());
        registerListener(new RetryStatisticsListener(new DefaultRetryStatisticsRepository()));
        registerListener(new LoggerAlarmRetryListener());
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
     * Getter method for property <tt>maxJobQuantityPerLoad</tt>.
     *
     * @return property value of maxJobQuantityPerLoad
     */
    public int getMaxJobQuantityPerLoad() {
        return maxJobQuantityPerLoad;
    }

    /**
     * Setter method for property <tt>maxJobQuantityPerLoad</tt>.
     *
     * @param maxJobQuantityPerLoad  value to be assigned to property maxJobQuantityPerLoad
     */
    public void setMaxJobQuantityPerLoad(int maxJobQuantityPerLoad) {
        this.maxJobQuantityPerLoad = maxJobQuantityPerLoad;
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