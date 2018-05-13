package org.walkerljl.retry.exception;

/**
 * 错误码
 *
 * @author xingxun
 * @Date 2017/10/22
 */
public enum RetryErrorCode implements ErrorCode {

    /**
     * 未知异常
     */
    UNKNOWN("unknown", "网络繁忙，轻稍后再试。"),

    /**
     * 无效的参数
     */
    INVALID_PARAM("invalid_param", "无效的参数"),;

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
