package org.walkerljl.retry.exception;

/**
 * Retry error code
 *
 * @author xingxun
 * @Date 2017/10/22
 */
public enum RetryErrorCode implements ErrorCode {

    /**
     * Unkown
     */
    UNKOWN("1", "unkown"),

    /**
     * Invalid of parameter
     */
    INVALID_PARAM("2", "Invalid of parameter"),;

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
    RetryErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
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
