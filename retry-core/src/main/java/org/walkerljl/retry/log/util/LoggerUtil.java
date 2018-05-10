package org.walkerljl.retry.log.util;

import java.text.MessageFormat;
import java.util.UUID;

import org.walkerljl.retry.log.LogConstants;
import org.walkerljl.retry.log.logger.Logger;

/**
 * LoggerUtil
 *
 * @author xingxun
 */
public class LoggerUtil extends LogConstants {

    public static void debug(Logger logger, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug(getLogMessageString(message));
        }
    }

    public static void debug(Logger logger, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug(render(template, parameters));
        }
    }

    public static void info(Logger logger, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info(getLogMessageString(message));
        }
    }

    public static void info(Logger logger, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info(render(template, parameters));
        }
    }

    public static void warn(Logger logger, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isWarnEnabled()) {
            logger.warn(getLogMessageString(message));
        }
    }

    public static void warn(Logger logger, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        if (logger.isWarnEnabled()) {
            logger.warn(render(template, parameters));
        }
    }

    public static void warn(Logger logger, Throwable e, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isWarnEnabled()) {
            String logMessageString = String.format("%s%s%s",
                    getExceptionMessageString(e),
                    LOG_SEPERATOR,
                    getLogMessageString(message));
            logger.warn(logMessageString, e);
        }
    }

    public static void warn(Logger logger, Throwable e, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        String logMessageString = String.format("%s%s%s",
                getExceptionMessageString(e),
                LOG_SEPERATOR,
                render(template, parameters));
        if (logger.isWarnEnabled()) {
            logger.warn(logMessageString, e);
        }
    }

    public static void error(Logger logger, Throwable e) {

        if (logger == null) {
            return;
        }

        logger.error(getExceptionMessageString(e), e);
    }

    public static void error(Logger logger, Throwable e, Object message) {

        if (logger == null) {
            return;
        }

        String logMessageString = String.format("%s%s%s",
                getExceptionMessageString(e),
                LOG_SEPERATOR,
                getLogMessageString(message));
        logger.error(logMessageString, e);
    }

    public static void error(Logger logger, Throwable e, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        String logMessageString = String.format("%s%s%s",
                getExceptionMessageString(e),
                LOG_SEPERATOR,
                render(template, parameters));
        logger.error(logMessageString, e);

    }

    private static String render(final String template, final Object... params) {
        if (params == null || params.length == 0) {
            return String.format("%s%s%s%s",
                    LOG_PREFIX,
                    getTraceId(),
                    LOG_SUFFIX,
                    template);
        }

        return String.format("%s%s%s%s",
                LOG_PREFIX,
                getTraceId(),
                LOG_SUFFIX,
                MessageFormat.format(template, params));
    }

    private static String getLogMessageString(Object message) {
        StringBuilder logMessageString = new StringBuilder();

        logMessageString.append(LOG_PREFIX);
        logMessageString.append(message);

        logMessageString.append(getTraceId());

        logMessageString.append(LOG_SUFFIX);

        return logMessageString.toString();
    }

    private static String getExceptionMessageString(Throwable e) {
        return (e == null ? "" : e.getMessage());
    }

    private static String getSerialNumber() {

        StringBuilder serialNumber = new StringBuilder();

        serialNumber.append(LOG_PARAM_PREFIX);
        serialNumber.append(UUID.randomUUID().toString().replaceAll("-", ""));
        serialNumber.append(LOG_PARAM_SUFFIX);

        return serialNumber.toString();
    }

    private static String getTraceId() {
        return getSerialNumber();
    }
}