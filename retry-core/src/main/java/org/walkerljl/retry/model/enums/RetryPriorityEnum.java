package org.walkerljl.retry.model.enums;

/**
 * 重试优先级
 *
 * @author xingxun
 */
public enum RetryPriorityEnum implements IEnum {

    /**
     * 高
     */
    HIGH("1", "高"),

    /**
     * 中
     */
    NORMAL("2", "中"),

    /**
     * 低
     */
    LOW("3", "低"),;

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