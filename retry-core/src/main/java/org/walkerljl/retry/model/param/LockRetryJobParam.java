package org.walkerljl.retry.model.param;

import java.util.Date;

import org.walkerljl.retry.model.BaseEntity;

/**
 * 锁定重试任务参数
 *
 * @author xingxun
 */
public class LockRetryJobParam extends BaseEntity {

    private static final long serialVersionUID = -740675484484440817L;

    /** 重试任务ID*/
    private String retryJobId;
    /** 重试超时时间*/
    private Long   retryTimeout;
    /** 上次重试时间*/
    private Date   lastRetryTime;
    /** 更新时间*/
    private Date   modifiedTime;

    /**
     * Getter method for property <tt>retryJobId</tt>.
     *
     * @return property value of retryJobId
     */
    public String getRetryJobId() {
        return retryJobId;
    }

    /**
     * Setter method for property <tt>retryJobId</tt>.
     *
     * @param retryJobId  value to be assigned to property retryJobId
     */
    public void setRetryJobId(String retryJobId) {
        this.retryJobId = retryJobId;
    }

    /**
     * Getter method for property <tt>retryTimeout</tt>.
     *
     * @return property value of retryTimeout
     */
    public Long getRetryTimeout() {
        return retryTimeout;
    }

    /**
     * Setter method for property <tt>retryTimeout</tt>.
     *
     * @param retryTimeout  value to be assigned to property retryTimeout
     */
    public void setRetryTimeout(Long retryTimeout) {
        this.retryTimeout = retryTimeout;
    }

    /**
     * Getter method for property <tt>lastRetryTime</tt>.
     *
     * @return property value of lastRetryTime
     */
    public Date getLastRetryTime() {
        return lastRetryTime;
    }

    /**
     * Setter method for property <tt>lastRetryTime</tt>.
     *
     * @param lastRetryTime  value to be assigned to property lastRetryTime
     */
    public void setLastRetryTime(Date lastRetryTime) {
        this.lastRetryTime = lastRetryTime;
    }

    /**
     * Getter method for property <tt>modifiedTime</tt>.
     *
     * @return property value of modifiedTime
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * Setter method for property <tt>modifiedTime</tt>.
     *
     * @param modifiedTime  value to be assigned to property modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}