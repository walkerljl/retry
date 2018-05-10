package org.walkerljl.retry.exception.machine;

import org.walkerljl.retry.exception.ErrorCode;

/**
 * Exception for can not stop
 *
 * @author xingxun
 */
public class CannotStopMachineException extends MachineException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * Constructor
     */
    public CannotStopMachineException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message Message
     */
    public CannotStopMachineException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param message Message
     */
    public CannotStopMachineException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructor
     *
     * @param e Throable
     */
    public CannotStopMachineException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param message Message
     * @param e Throable
     */
    public CannotStopMachineException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Constructor
     *
     * @param code Error code
     * @param e Throable
     */
    public CannotStopMachineException(ErrorCode code, Throwable e) {
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
    public CannotStopMachineException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}