package org.walkerljl.retry.db.dao.dataobject;

import java.util.Date;

import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.standard.model.BaseEntity;

/**
 *
 * @author lijunlin
 */
public class BaseDO extends BaseEntity {

    private static final long serialVersionUID = -8369511533254279625L;

    /** 主键ID*/
    @Column(value = "id", key = true)
    private Long id;
    /** 扩展信息*/
    @Column("ext_info")
    private String extInfo;
    /** 备注*/
    @Column("remark")
    private String remark;
    /** 状态*/
    @Column("status")
    private String status;
    /** 创建者*/
    @Column("creator")
    private String creator;
    /** 创建时间*/
    @Column("gmt_create")
    private Date   created;
    /** 修改者*/
    @Column("modifier")
    private String modifier;
    /** 修改时间*/
    @Column("gmt_modified")
    private Date   modified;

    public BaseDO() {}

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id  value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>extInfo</tt>.
     *
     * @return property value of extInfo
     */
    public String getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo  value to be assigned to property extInfo
     */
    public void setExtInfo(String extInfo) {
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
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(String status) {
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
}