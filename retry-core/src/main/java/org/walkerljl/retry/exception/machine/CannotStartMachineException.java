package org.walkerljl.retry.exception.machine;

import org.walkerljl.retry.exception.ErrorCode;

/**
 * Exception for can not start
 *
 * @author xingxun
 */
public class CannotStartMachineException extends MachineException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * Constructor
     */
    public CannotStartMachineException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message Message
     */
    public CannotStartMachineException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param message Message
     */
    public CannotStartMachineException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructor
     *
     * @param e Throable
     */
    public CannotStartMachineException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param message Message
     * @param e Throable
     */
    public CannotStartMachineException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param e Throable
     */
    public CannotStartMachineException(ErrorCode code, Throwable e) {
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
    public CannotStartMachineException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}