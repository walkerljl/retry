package org.walkerljl.retry.exception;

/**
 * RetryException
 *
 * @author xingxun
 */
public class RetryException extends RuntimeException {

    private static final long serialVersionUID = 5859603978158811148L;

    /**
     * Error code
     */
    protected ErrorCode code;

    /**
     * Constructor
     */
    public RetryException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message Message
     */
    public RetryException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code Error code
     */
    public RetryException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * Constructor
     *
     * @param e Throable
     */
    public RetryException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param message Message
     */
    public RetryException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructor
     *
     * @param message Message
     * @param e Throable
     */
    public RetryException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param e Throable
     */
    public RetryException(ErrorCode code, Throwable e) {
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
    public RetryException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}