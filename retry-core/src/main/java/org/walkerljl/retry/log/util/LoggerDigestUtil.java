package org.walkerljl.retry.log.util;

import org.walkerljl.retry.log.invocation.InvocationInfo;
import org.walkerljl.retry.log.logger.Logger;

/**
 * Logger digest util
 *
 * @author xingxun
 */
public class LoggerDigestUtil extends AbstractLogUtil {

    /**
     * Log digest
     *
     * @param invocationInfo InvocationInfo
     * @param logger Logger
     */
    public static void logDigest(InvocationInfo invocationInfo, Logger logger) {
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
        sb.append(LOG_SEPERATOR);
        sb.append(getString(null));
        sb.append(LOG_SEPERATOR);
        sb.append(invocationInfo.getCostTime());
        sb.append(TIME_UNIT);
        sb.append(LOG_PARAM_SUFFIX);

        sb.append(LOG_PARAM_PREFIX);
        sb.append(getString(invocationInfo.getAppName()));
        sb.append(LOG_PARAM_SUFFIX);

        LoggerUtil.info(logger, sb.toString());
    }
}