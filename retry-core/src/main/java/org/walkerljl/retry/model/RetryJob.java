package org.walkerljl.retry.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;

/**
 * Retry job
 *
 * @author xingxun
 */
public class RetryJob extends BaseEntity {

    private static final long serialVersionUID = -6017516180911424002L;

    /** ID*/
    private String              id;
    /** Business type*/
    private String              bizType;
    /** Business id*/
    private String              bizId;
    /** Priority*/
    private RetryPriorityEnum   priority;
    /** Attempts*/
    private Integer             attempts;
    /** Max attempts*/
    private Integer             maxAttempts;
    /** Target identifier*/
    private String              targetIdentifer;
    /** Parameter list*/
    private List<RetryParam>    params;
    /** Last retry time*/
    private Date                lastRetryTime;
    /** Next retry time*/
    private Date                nextRetryTime;
    /** Retry rule*/
    private String              retryRule;
    /** Status*/
    private RetryJobStatusEnum  status;
    /** Extend information*/
    private Map<String, String> extInfo;
    /** Remark*/
    private String              remark;
    /** Creator*/
    private String              creator;
    /** Created time*/
    private Date                created;
    /** Modifier*/
    private String              modifier;
    /** Modified time*/
    private Date                modified;

    /**
     * Constructor
     */
    public RetryJob() {}

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
     * Getter method for property <tt>bizType</tt>.
     *
     * @return property value of bizType
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * Setter method for property <tt>bizType</tt>.
     *
     * @param bizType  value to be assigned to property bizType
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * Getter method for property <tt>bizId</tt>.
     *
     * @return property value of bizId
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * Setter method for property <tt>bizId</tt>.
     *
     * @param bizId  value to be assigned to property bizId
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * Getter method for property <tt>priority</tt>.
     *
     * @return property value of priority
     */
    public RetryPriorityEnum getPriority() {
        return priority;
    }

    /**
     * Setter method for property <tt>priority</tt>.
     *
     * @param priority  value to be assigned to property priority
     */
    public void setPriority(RetryPriorityEnum priority) {
        this.priority = priority;
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
     * Getter method for property <tt>maxAttempts</tt>.
     *
     * @return property value of maxAttempts
     */
    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Setter method for property <tt>maxAttempts</tt>.
     *
     * @param maxAttempts  value to be assigned to property maxAttempts
     */
    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    /**
     * Getter method for property <tt>targetIdentifer</tt>.
     *
     * @return property value of targetIdentifer
     */
    public String getTargetIdentifer() {
        return targetIdentifer;
    }

    /**
     * Setter method for property <tt>targetIdentifer</tt>.
     *
     * @param targetIdentifer  value to be assigned to property targetIdentifer
     */
    public void setTargetIdentifer(String targetIdentifer) {
        this.targetIdentifer = targetIdentifer;
    }

    /**
     * Getter method for property <tt>params</tt>.
     *
     * @return property value of params
     */
    public List<RetryParam> getParams() {
        return params;
    }

    /**
     * Setter method for property <tt>params</tt>.
     *
     * @param params  value to be assigned to property params
     */
    public void setParams(List<RetryParam> params) {
        this.params = params;
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
     * Getter method for property <tt>retryRule</tt>.
     *
     * @return property value of retryRule
     */
    public String getRetryRule() {
        return retryRule;
    }

    /**
     * Setter method for property <tt>retryRule</tt>.
     *
     * @param retryRule  value to be assigned to property retryRule
     */
    public void setRetryRule(String retryRule) {
        this.retryRule = retryRule;
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
     * Getter method for property <tt>extInfo</tt>.
     *
     * @return property value of extInfo
     */
    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo  value to be assigned to property extInfo
     */
    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     *
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     *
     * @param remark  value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    /**
     * Getter method for property <tt>modifier</tt>.
     *
     * @return property value of modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Setter method for property <tt>modifier</tt>.
     *
     * @param modifier  value to be assigned to property modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
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