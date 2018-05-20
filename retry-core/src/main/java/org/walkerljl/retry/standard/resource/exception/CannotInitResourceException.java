package org.walkerljl.retry.standard.resource.exception;


import org.walkerljl.retry.exception.ErrorCode;

/**
 * Exception of resource can not init
 *
 * @author xingxun
 */
public class CannotInitResourceException extends ResourceException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public CannotInitResourceException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public CannotInitResourceException(String message) {
        super(message);
    }


    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public CannotInitResourceException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public CannotInitResourceException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     */
    public CannotInitResourceException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public CannotInitResourceException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public CannotInitResourceException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public CannotInitResourceException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}