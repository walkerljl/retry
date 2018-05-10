package org.walkerljl.retry.model;

import java.util.Date;
import java.util.Map;

import org.walkerljl.retry.model.enums.RetryParamStatusEnum;

/**
 * Retry parameter.
 *
 * @author xingxun
 */
public class RetryParam extends BaseEntity {

    private static final long serialVersionUID = -8640391014168493273L;

    /** Retry job id*/
    private String               retryJobId;
    /**  Parameter value*/
    private String               value;
    /** Extended information*/
    private Map<String, String>  extInfo;
    /** Reamrk*/
    private String               remark;
    /** Status*/
    private RetryParamStatusEnum status;
    /** Creator*/
    private String               creator;
    /** Create time*/
    private Date                 created;
    /** Modifier*/
    private String               modifier;
    /** Modified time*/
    private Date                 modified;

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