package org.walkerljl.retry.exception.resouce;

import org.walkerljl.retry.exception.ErrorCode;

/**
 * Exception of resource can not destroy
 *
 * @author xingxun
 */
public class CannotDestroyResourceException extends ResourceException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * Constructor
     */
    public CannotDestroyResourceException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message Message
     */
    public CannotDestroyResourceException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param message Message
     */
    public CannotDestroyResourceException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructor
     *
     * @param e Throable
     */
    public CannotDestroyResourceException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param message Message
     * @param e Throable
     */
    public CannotDestroyResourceException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param e Throable
     */
    public CannotDestroyResourceException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param message Message
     * @param e Throable
     */
    public CannotDestroyResourceException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}