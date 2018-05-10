package org.walkerljl.retry.alarm;

import org.walkerljl.retry.model.BaseEntity;

/**
 * Alarm information
 *
 * @author xingxun
 */
public class AlarmInfo extends BaseEntity {

    private static final long serialVersionUID = -5115754845077396488L;

    /** Key*/
    private String key;
    /** Description*/
    private String description;

    public AlarmInfo(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /**
     * Getter method for property <tt>key</tt>.
     *
     * @return property value of key
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter method for property <tt>key</tt>.
     *
     * @param key  value to be assigned to property key
     */
    public void setKey(String key) {
        this.key = key;
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

    @Override
    public String toString() {
        return super.toString();
    }
}