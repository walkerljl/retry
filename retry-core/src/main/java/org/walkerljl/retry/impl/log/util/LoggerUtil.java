package org.walkerljl.retry.impl.log.util;

import java.text.MessageFormat;
import java.util.UUID;

import org.walkerljl.retry.impl.log.LogConstants;
import org.walkerljl.retry.logger.Logger;

/**
 * LoggerUtil
 *
 * @author xingxun
 */
public class LoggerUtil extends LogConstants {

    /**
     * 记录Debug级别日志
     *
     * <ul>
     *     <li>日志对象为Null将不记录任何日志信息</li>
     * </ul>
     * @param logger 日志对象
     * @param message 日志内容
     */
    public static void debug(Logger logger, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug(getLogMessageString(message));
        }
    }

    /**
     * 记录Debug级别日志
     * <ul>
     *     <li>日志对象为Null将不记录任何日志信息</li>
     * </ul>
     * @param logger 日志对象
     * @param template 内容模版
     * @param parameters 内容参数
     */
    public static void debug(Logger logger, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug(render(template, parameters));
        }
    }

    /**
     * 记录INFO级别的日志
     *
     * @param logger 日志对象
     * @param message 日志内容
     */
    public static void info(Logger logger, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info(getLogMessageString(message));
        }
    }

    /**
     * 记录INFO级别的日志
     *
     * @param logger 日志对象
     * @param template 日志模版
     * @param parameters 日志参数
     */
    public static void info(Logger logger, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info(render(template, parameters));
        }
    }

    /**
     * 打印WARN级别的日志
     *
     * @param logger 日志对象
     * @param message 日志内容
     */
    public static void warn(Logger logger, Object message) {

        if (logger == null) {
            return;
        }

        if (logger.isWarnEnabled()) {
            logger.warn(getLogMessageString(message));
        }
    }

    /**
     * 打印WARN级别的日志
     *
     * @param logger 日志对象
     * @param template 内容模版
     * @param parameters 内容参数
     */
    public static void warn(Logger logger, String template, Object... parameters) {

        if (logger == null) {
            return;
        }

        if (logger.isWarnEnabled()) {
            logger.warn(render(template, parameters));
        }
    }

    /**
     * 打印WARN级别的日志
     *
     * @param logger 日志对象
     * @param e 异常对象
     * @param message 日志内容
     */
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

    /**
     * 打印WARN级别的日志
     *
     * @param logger 日志对象
     * @param e 异常对象
     * @param template 内容模版
     * @param parameters 内容参数
     */
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

    /**
     * 打印ERROR级别的日志
     *
     * @param logger 日志对象
     * @param e 日志对象
     */
    public static void error(Logger logger, Throwable e) {

        if (logger == null) {
            return;
        }

        logger.error(getExceptionMessageString(e), e);
    }

    /**
     * 打印ERROR级别的日志
     *
     * @param logger 日志对象
     * @param e 异常对象
     * @param message 日志内容
     */
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

    /**
     * 打印ERROR级别日志
     *
     * @param logger 日志对象
     * @param e 异常对象
     * @param template 内容模版
     * @param parameters 内容参数
     */
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

    /**
     * 渲染日志内容
     *
     * @param template 内容模版
     * @param params 内容参数
     * @return
     */
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

    /**
     * 获取String的日志内容
     *
     * @param message
     * @return
     */
    private static String getLogMessageString(Object message) {
        StringBuilder logMessageString = new StringBuilder();

        logMessageString.append(LOG_PREFIX);
        logMessageString.append(message);

        // 加入日志流水号
        logMessageString.append(getTraceId());

        logMessageString.append(LOG_SUFFIX);

        return logMessageString.toString();
    }

    /**
     * 获取String类型的异常内容
     *
     * @param e 异常对象
     * @return
     */
    private static String getExceptionMessageString(Throwable e) {
        return (e == null ? "" : e.getMessage());
    }

    /**
     * 获取流水号
     *
     * @return
     */
    private static String getSerialNumber() {

        StringBuilder serialNumber = new StringBuilder();

        serialNumber.append(LOG_PARAM_PREFIX);
        serialNumber.append(UUID.randomUUID().toString().replaceAll("-", ""));
        serialNumber.append(LOG_PARAM_SUFFIX);

        return serialNumber.toString();
    }

    /**
     * 获取TraceId
     *
     * @return
     */
    private static String getTraceId() {
        return getSerialNumber();
    }

}