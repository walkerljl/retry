package org.walkerljl.retry.model.enums;

/**
 * 重试参数状态
 *
 * @author xingxun
 */
public enum RetryParamStatusEnum implements IEnum {

    /**
     * 正常
     */
    NORMAL("normal", "正常"),

    /**
     * 已删除
     */
    DELETED("deleted", "已删除"),;

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