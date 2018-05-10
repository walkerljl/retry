package org.walkerljl.retry.model;

import java.util.Date;
import java.util.Map;

import org.walkerljl.retry.model.enums.RetryLogStatusEnum;

/**
 * Retry log
 *
 * @author lijunlin
 */
public class RetryLog extends BaseEntity {

    private static final long serialVersionUID = -8074939915651656172L;

    /** Retry job id*/
    private String              retryJobId;
    /** Attempts*/
    private Integer             attempts;
    /** Status*/
    private RetryLogStatusEnum  status;
    /** Description*/
    private String              description;
    /** Extend info*/
    private Map<String, Object> extInfo;
    /** Creator*/
    private String              creator;
    /** Created time*/
    private Date                created;

    /**
     * Constructor
     */
    public RetryLog() {}

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
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public RetryLogStatusEnum getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(RetryLogStatusEnum status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property <tt>description</tt>.
     *
     * @param description  value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for property <tt>extInfo</tt>.
     *
     * @return property value of extInfo
     */
    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo  value to be assigned to property extInfo
     */
    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    /**
     * Getter method for property <tt>creator</tt>.
     *
     * @return property value of creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Setter method for property <tt>creator</tt>.
     *
     * @param creator  value to be assigned to property creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Getter method for property <tt>created</tt>.
     *
     * @return property value of created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Setter method for property <tt>created</tt>.
     *
     * @param created  value to be assigned to property created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}