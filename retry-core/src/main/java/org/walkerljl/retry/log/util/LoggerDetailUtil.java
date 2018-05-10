package org.walkerljl.retry.log.util;

import java.util.Arrays;

import org.walkerljl.retry.log.invocation.InvocationInfo;
import org.walkerljl.retry.log.logger.Logger;

/**
 * Logger detail util
 *
 * @author xingxun
 */
public class LoggerDetailUtil extends AbstractLogUtil {

    /**
     * Log detail
     *
     * @param invocationInfo InvocationInfo
     * @param logger Logger
     */
    public static void logDetail(InvocationInfo invocationInfo, Logger logger) {
        if (invocationInfo == null) {
            return;
        }
        if (logger == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(LOG_PARAM_PREFIX);
        sb.append(getString(invocationInfo.getServiceName()));
        sb.append(LOG_SEPERATOR);
        sb.append(getString(invocationInfo.isSuccess()));
        sb.append(LOG_PARAM_SUFFIX);

        sb.append(LOG_PARAM_PREFIX).append(Arrays.toString(invocationInfo.getParams()));

        sb.append(LOG_PARAM_SUFFIX).append(LOG_PARAM_PREFIX);
        sb.append(null == invocationInfo.getResultData() ? LOG_DEFAULT : invocationInfo.getResultData()).append(LOG_PARAM_SUFFIX);
        //Error
        sb.append(LOG_PARAM_PREFIX).append(THROWABLE);
        String message = LOG_DEFAULT;
        if (invocationInfo.getThrowable() != null) {
            message = invocationInfo.getThrowable().getMessage();
        }
        sb.append(message).append(LOG_PARAM_SUFFIX);

        LoggerUtil.info(logger, sb.toString());
    }
}