package org.walkerljl.retry.log.logger;

/**
 * @author xingxun
 */
public interface Logger {

    // ----------------------------------------------------- Logging Properties

    /**
     * <p> Is trace logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than trace. </p>
     *
     * @return true if trace is enabled in the underlying logger.
     */
    boolean isTraceEnabled();

    /**
     * <p> Is debug logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than debug. </p>
     *
     * @return true if debug is enabled in the underlying logger.
     */
    boolean isDebugEnabled();

    /**
     * <p> Is info logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than info. </p>
     *
     * @return true if info is enabled in the underlying logger.
     */
    boolean isInfoEnabled();

    /**
     * <p> Is warn logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than warn. </p>
     *
     * @return true if warn is enabled in the underlying logger.
     */
    boolean isWarnEnabled();

    /**
     * <p> Is error logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than error. </p>
     *
     * @return true if error is enabled in the underlying logger.
     */
    boolean isErrorEnabled();

    // -------------------------------------------------------- Logging Methods

    /**
     * <p> Log a message with trace log level. </p>
     *
     * @param message log this message
     */
    void trace(Object message);

    /**
     * <p> Log an error with trace log level. </p>
     *
     * @param message log this message
     * @param e log this cause
     */
    void trace(Object message, Throwable e);

    /**
     * <p> Log a message with debug log level. </p>
     *
     * @param message log this message
     */
    void debug(Object message);

    /**
     * <p> Log an error with debug log level. </p>
     *
     * @param message log this message
     * @param e log this cause
     */
    void debug(Object message, Throwable e);

    /**
     * <p> Log a message with info log level. </p>
     *
     * @param message log this message
     */
    void info(Object message);

    /**
     * <p> Log an error with info log level. </p>
     *
     * @param message log this message
     * @param e log this cause
     */
    void info(Object message, Throwable e);

    /**
     * <p> Log a message with warn log level. </p>
     *
     * @param message log this message
     */
    void warn(Object message);

    /**
     * <p> Log an error with warn log level. </p>
     *
     * @param message log this message
     * @param e log this cause
     */
    void warn(Object message, Throwable e);

    /**
     * <p> Log a message with error log level. </p>
     *
     * @param message log this message
     */
    void error(Object message);

    /**
     * <p> Log an error with error log level. </p>
     *
     * @param message log this message
     * @param e log this cause
     */
    void error(Object message, Throwable e);
}