package org.walkerljl.retry.standard.resource.exception;

import org.walkerljl.retry.exception.ErrorCode;
import org.walkerljl.retry.exception.RetryException;

/**
 * Exception of resource
 *
 * @author xingxun
 */
public class ResourceException extends RetryException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public ResourceException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public ResourceException(String message) {
        super(message);
    }


    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public ResourceException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public ResourceException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public ResourceException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public ResourceException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public ResourceException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public ResourceException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}