package org.walkerljl.retry.model.enums;

/**
 * 重试任务状态
 *
 * @author xingxun
 */
public enum RetryJobStatusEnum implements IEnum {

    /**
     * 未处理
     */
    UNPROCESS("unprocess", "未处理"),

    /**
     * 处理中
     */
    PROCESSING("processing", "处理中"),

    /**
     * 已处理
     */
    PROCESSED("processed", "已处理"),

    /**
     * 处理失败
     */
    FAILURE("failure", "处理失败"),

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
    RetryJobStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Get enumeration by type
     *
     * @param code code
     * @return
     */
    public static RetryJobStatusEnum getType(String code) {
        for (RetryJobStatusEnum ele : RetryJobStatusEnum.values()) {
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