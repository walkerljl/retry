package org.walkerljl.retry.model.param;

import java.util.Date;

import org.walkerljl.retry.model.BaseEntity;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;

/**
 * ReleaseRetryJobParam
 *
 * @author xingxun
 */
public class ReleaseRetryJobParam extends BaseEntity {

    private static final long serialVersionUID = -910706720620475692L;

    /** Retry job id*/
    private String             retryJobId;
    /** Retry job status*/
    private RetryJobStatusEnum status;
    /** Next retry time*/
    private Date               nextRetryTime;
    /** Attempts*/
    private Integer            attempts;
    /** Modified time*/
    private Date               modified;

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
     * Getter method for property <tt>attempts</tt>.
     *
     * @return property value of attempts
     */
    public Integer getAttempts() {
        return attempts;
    }

    /**
     * Setter method for property <tt>attempts</tt>.
     *
     * @param attempts  value to be assigned to property attempts
     */
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    /**
     * Getter method for property <tt>modified</tt>.
     *
     * @return property value of modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * Setter method for property <tt>modified</tt>.
     *
     * @param modified  value to be assigned to property modified
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}