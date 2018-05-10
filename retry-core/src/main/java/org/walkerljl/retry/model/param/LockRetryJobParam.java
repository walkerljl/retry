package org.walkerljl.retry.model.param;

import java.util.Date;

import org.walkerljl.retry.model.BaseEntity;

/**
 * Parameter of lock retry job
 *
 * @author xingxun
 */
public class LockRetryJobParam extends BaseEntity {

    private static final long serialVersionUID = -740675484484440817L;

    /** Retry job id*/
    private String retryJobId;
    /** Last retry time*/
    private Date   lastRetryTime;
    /** Modified time*/
    private Date   modified;

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