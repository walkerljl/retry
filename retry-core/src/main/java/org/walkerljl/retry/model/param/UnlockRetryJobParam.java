package org.walkerljl.retry.model.param;

import java.util.Date;

import org.walkerljl.retry.model.BaseEntity;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;

/**
 * 解锁重试任务参数
 *
 * @author xingxun
 */
public class UnlockRetryJobParam extends BaseEntity {

    private static final long serialVersionUID = -910706720620475692L;

    /** 重试任务ID*/
    private String             retryJobId;
    /** 重试任务状态*/
    private RetryJobStatusEnum status;
    /** 下次重试时间*/
    private Date               nextRetryTime;
    /** 更新时间*/
    private Date               modifiedTime;

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
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public RetryJobStatusEnum getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(RetryJobStatusEnum status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>nextRetryTime</tt>.
     *
     * @return property value of nextRetryTime
     */
    public Date getNextRetryTime() {
        return nextRetryTime;
    }

    /**
     * Setter method for property <tt>nextRetryTime</tt>.
     *
     * @param nextRetryTime  value to be assigned to property nextRetryTime
     */
    public void setNextRetryTime(Date nextRetryTime) {
        this.nextRetryTime = nextRetryTime;
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