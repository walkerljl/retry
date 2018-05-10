package org.walkerljl.retry.model.enums;

/**
 * Retry log status
 *
 * @author xingxun
 */
public enum RetryLogStatusEnum implements IEnum {

    /**
     * Success
     */
    SUCCESS("success", "success"),

    /**
     * Failure
     */
    FAILURE("failure", "failure"),

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
    RetryLogStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Get enumeration by code
     *
     * @param code code
     * @return
     */
    public static RetryLogStatusEnum getType(String code) {

        for (RetryLogStatusEnum ele : RetryLogStatusEnum.values()) {
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