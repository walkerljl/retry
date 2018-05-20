package org.walkerljl.retry.exception;

/**
 * RetryException
 *
 * @author xingxun
 */
public class RetryException extends RuntimeException {

    private static final long serialVersionUID = 5859603978158811148L;

    /**
     * 异常码
     */
    protected ErrorCode code;

    /**
     * 默认构造函数
     */
    public RetryException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public RetryException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public RetryException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public RetryException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 消息
     */
    public RetryException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public RetryException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public RetryException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public RetryException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    /**
     * 获取异常码
     *
     * @return
     */
    public ErrorCode getCode() {
        return code;
    }
}