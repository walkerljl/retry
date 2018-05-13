package org.walkerljl.retry.listener.impl;

import java.util.List;

import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.impl.util.CollectionUtil;
import org.walkerljl.retry.impl.util.JSONUtil;
import org.walkerljl.retry.listener.RetryListener;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryJob;

/**
 * RetryListenerUtil
 *
 * @author xingxun
 */
public class RetryListenerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY);

    public static void doOnRunningInterceptors(List<RetryListener> retryListeners, RetryContext retryContext, RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        for (RetryListener listener : retryListeners) {
            try {
                listener.onRunning(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(listener, retryJob, retryContext, e));
            }
        }
    }

    public static void doOnCompletedInterceptors(List<RetryListener> retryListeners, RetryContext retryContext, RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        for (RetryListener listener : retryListeners) {
            try {
                listener.onCompleted(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(listener, retryJob, retryContext, e));
            }
        }
    }

    public static void doOnErrorInterceptors(List<RetryListener> retryListeners,
                                             RetryContext retryContext,
                                             RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        for (RetryListener listener : retryListeners) {
            try {
                listener.onError(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(listener, retryJob, retryContext, e));
            }
        }
    }

    public static void doOnAbortInterceptors(List<RetryListener> retryListeners, RetryContext retryContext, RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        for (RetryListener listener : retryListeners) {
            try {
                listener.onAbort(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(listener, retryJob, retryContext, e));
            }
        }
    }

    private static String buildLogContent(RetryListener retryListener,
                                          RetryJob retryJob,
                                          RetryContext retryContext,
                                          Throwable e) {
        return String.format("(%s)(%s)(%s)(%s)",
                (e == null ? "" : e.getMessage()),
                JSONUtil.toJSONString(retryListener),
                JSONUtil.toJSONString(retryContext),
                JSONUtil.toJSONString(retryJob)
                );
    }
}