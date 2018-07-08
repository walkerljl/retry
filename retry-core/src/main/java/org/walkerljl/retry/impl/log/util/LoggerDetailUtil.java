package org.walkerljl.retry.impl.log.util;

import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.util.JSONUtil;
import org.walkerljl.retry.logger.Logger;

/**
 * Logger detail util
 *
 * @author xingxun
 */
public class LoggerDetailUtil extends AbstractLogUtil {

    /**
     * 记录详细日志
     *
     * <ul>
     *     <li>调用或日志对象为Null将不记录任何日志</li>
     * </ul>
     *
     * @param invocationInfo 调用信息
     * @param logger 日志对象
     * @param <PARAM>
     * @param <RESULT>
     */
    public static <PARAM, RESULT> void logDetail(InvocationInfo<PARAM, RESULT> invocationInfo, Logger logger) {
        if (logger == null) {
            return;
        }
        if (logger.isInfoEnabled()) {
            String logContent = buildLogContent(invocationInfo);
            if (logContent == null) {
                return;
            }
            LoggerUtil.info(logger, logContent);
        }
    }

    /**
     * 构建日志内容
     *
     * @param invocationInfo 调用信息
     * @param <PARAM>
     * @param <RESULT>
     * @return
     */
    public static <PARAM, RESULT> String buildLogContent(InvocationInfo<PARAM, RESULT> invocationInfo) {
        if (invocationInfo == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(LOG_PARAM_PREFIX);
        sb.append(getString(invocationInfo.getServiceName()));
        sb.append(LOG_SEPERATOR);
        sb.append(getString(invocationInfo.isSuccess()));
        sb.append(LOG_PARAM_SUFFIX);

        sb.append(LOG_PARAM_PREFIX).append(JSONUtil.toJSONString(invocationInfo.getParam()));

        sb.append(LOG_PARAM_SUFFIX).append(LOG_PARAM_PREFIX);
        sb.append(null == invocationInfo.getDirectResultData() ? LOG_DEFAULT : JSONUtil.toJSONString(invocationInfo.getDirectResultData())).append(
                LOG_PARAM_SUFFIX);
        //异常
        sb.append(LOG_PARAM_PREFIX).append(THROWABLE);
        String message = LOG_DEFAULT;
        if (invocationInfo.getThrowable() != null) {
            message = invocationInfo.getThrowable().getMessage();
        }
        sb.append(message).append(LOG_PARAM_SUFFIX);

        return sb.toString();
    }
}