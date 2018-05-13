package org.walkerljl.retry.model;

import java.util.Date;
import java.util.Map;

import org.walkerljl.retry.model.enums.RetryParamStatusEnum;

/**
 * 重试参数
 *
 * @author xingxun
 */
public class RetryParam extends BaseEntity {

    private static final long serialVersionUID = -8640391014168493273L;

    /** 重试任务ID*/
    private String               retryJobId;
    /** 参数值*/
    private String               value;
    /** 扩展信息*/
    private Map<String, Object>  extInfo;
    /** 备注*/
    private String               remark;
    /** 状态*/
    private RetryParamStatusEnum status;
    /** 创建者*/
    private String               creator;
    /** 创建时间*/
    private Date                 createdTime;
    /** 修改者*/
    private String               modifier;
    /** 修改时间*/
    private Date                 modifiedTime;

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
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value  value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
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
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public RetryParamStatusEnum getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(RetryParamStatusEnum status) {
        this.status = status;
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
     * Getter method for property <tt>createdTime</tt>.
     *
     * @return property value of createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Setter method for property <tt>createdTime</tt>.
     *
     * @param createdTime  value to be assigned to property createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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