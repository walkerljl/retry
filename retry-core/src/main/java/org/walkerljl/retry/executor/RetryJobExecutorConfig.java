package org.walkerljl.retry.executor;

import org.walkerljl.retry.model.BaseEntity;

/**
 * RetryJobExecutorConfig
 *
 * @author xingxun
 */
public class RetryJobExecutorConfig extends BaseEntity {

    private static final long serialVersionUID = -35957084533527965L;

    /** ID*/
    private String id;
    /** ThreadPoolExecutor config:workQueueSize*/
    private       int workQueueSize = 1000;
    /**  ThreadPoolExecutor config:keepAliveTime */
    private       int keepAliveTime = 60;
    /** ThreadPoolExecutor config:corePoolSize */
    private final int corePoolSize  = 10;
    /** ThreadPoolExecutor config:maxPoolSize */
    private       int maxPoolSize   = 30;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id  value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>workQueueSize</tt>.
     *
     * @return property value of workQueueSize
     */
    public int getWorkQueueSize() {
        return workQueueSize;
    }

    /**
     * Setter method for property <tt>workQueueSize</tt>.
     *
     * @param workQueueSize  value to be assigned to property workQueueSize
     */
    public void setWorkQueueSize(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    /**
     * Getter method for property <tt>keepAliveTime</tt>.
     *
     * @return property value of keepAliveTime
     */
    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    /**
     * Setter method for property <tt>keepAliveTime</tt>.
     *
     * @param keepAliveTime  value to be assigned to property keepAliveTime
     */
    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    /**
     * Getter method for property <tt>corePoolSize</tt>.
     *
     * @return property value of corePoolSize
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * Getter method for property <tt>maxPoolSize</tt>.
     *
     * @return property value of maxPoolSize
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * Setter method for property <tt>maxPoolSize</tt>.
     *
     * @param maxPoolSize  value to be assigned to property maxPoolSize
     */
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}