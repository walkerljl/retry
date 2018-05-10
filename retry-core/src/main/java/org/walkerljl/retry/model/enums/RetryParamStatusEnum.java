package org.walkerljl.retry.model.enums;

/**
 * Retry param status
 *
 * @author xingxun
 */
public enum RetryParamStatusEnum implements IEnum {

    /**
     * Normal
     */
    NORMAL("normal", "normal"),

    /**
     * Deleted
     */
    DELETED("deleted", "deleted"),;

    /** Code*/
    private String code;
    /** Description*/
    private String description;

    /**
     * Constructor
     *
     * @param code code
     * @param description description
     */
    RetryParamStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Get enumeration by code
     *
     * @param code code
     * @return
     */
    public static RetryParamStatusEnum getType(String code) {
        for (RetryParamStatusEnum ele : RetryParamStatusEnum.values()) {
            if (ele.getCode().equalsIgnoreCase(code)) {
                return ele;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}