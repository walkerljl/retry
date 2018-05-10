package org.walkerljl.retry.model.enums;

/**
 * Retry priority
 *
 * @author xingxun
 */
public enum RetryPriorityEnum implements IEnum {

    /**
     * High
     */
    HIGH("1", "high"),

    /**
     * Normal
     */
    NORMAL("2", "normal"),

    /**
     * Low
     */
    LOW("3", "low"),;

    /** Code */
    private String code;
    /** Description */
    private String description;

    /**
     * Constructor
     *
     * @param code code
     * @param description description
     */
    RetryPriorityEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Get enumeration by code
     *
     * @param code code
     * @return
     */
    public static RetryPriorityEnum getType(String code) {
        for (RetryPriorityEnum ele : RetryPriorityEnum.values()) {
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