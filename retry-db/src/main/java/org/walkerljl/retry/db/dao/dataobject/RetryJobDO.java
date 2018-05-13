package org.walkerljl.retry.db.dao.dataobject;

import java.util.Date;

import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Table;

/**
 * 重试任务
 *
 * @author xingxun
 */
@Table("retry_job")
public class RetryJobDO extends BaseDO {

    private static final long serialVersionUID = -141202743737612185L;

    /** 业务类型*/
    @Column("biz_type")
    private String  bizType;
    /** 业务ID*/
    @Column("biz_id")
    private String  bizId;
    /** 优先级*/
    @Column("priority")
    private Integer priority;
    /** 当前重试次数*/
    @Column("attempts")
    private Integer attempts;
    /** 最大重试次数*/
    @Column("max_attempts")
    private Integer maxAttempts;
    /** 目标标识符*/
    @Column("target_identifier")
    private String  targetIdentifier;
    /** 重试规则*/
    @Column("retry_rule")
    private String  retryRule;
    /** 上次重试时间*/
    @Column("last_retry_time")
    private Date    lastRetryTime;
    /** 下次重试时间*/
    @Column("next_retry_time")
    private Date    nextRetryTime;

    public RetryJobDO() {}

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
    public Integer getPriority() {
        return priority;
    }

    /**
     * Setter method for property <tt>priority</tt>.
     *
     * @param priority  value to be assigned to property priority
     */
    public void setPriority(Integer priority) {
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
     * Getter method for property <tt>targetIdentifier</tt>.
     *
     * @return property value of targetIdentifier
     */
    public String getTargetIdentifier() {
        return targetIdentifier;
    }

    /**
     * Setter method for property <tt>targetIdentifier</tt>.
     *
     * @param targetIdentifier  value to be assigned to property targetIdentifier
     */
    public void setTargetIdentifier(String targetIdentifier) {
        this.targetIdentifier = targetIdentifier;
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

    @Override
    public String toString() {
        return super.toString();
    }
}