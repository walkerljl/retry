package org.walkerljl.retry.model.enums;

/**
 * Retry job status
 *
 * @author xingxun
 */
public enum RetryJobStatusEnum implements IEnum {

    /**
     * Unprocess
     */
    UNPROCESS("unprocess", "unprocess"),

    /**
     * Processing
     */
    PROCESSING("processing", "processing"),

    /**
     * Processed
     */
    PROCESSED("processed", "processed"),

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