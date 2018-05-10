package org.walkerljl.retry.db.dao.dataobject;

import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Table;

/**
 * 重试参数DO
 *
 * @author xingxun
 */
@Table("retry_param")
public class RetryParamDO extends BaseDO {

    private static final long serialVersionUID = -8640391014168493273L;

    /** 重试任务ID*/
    @Column("retry_job_id")
    private String              retryJobId;
    /**  参数值*/
    @Column("value")
    private String              value;

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
}