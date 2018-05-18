package org.walkerljl.retry.listener.impl.util;

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

    /**
     * doOnRunningInterceptors
     *
     * @param retryListeners
     * @param retryContext
     * @param retryJob
     */
    public static void doOnRunningInterceptors(List<RetryListener> retryListeners, RetryContext retryContext, RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        if (retryContext == null) {
            return;
        }
        if (retryJob == null) {
            return;
        }
        for (RetryListener retryListener : retryListeners) {
            if (retryListener == null) {
                continue;
            }
            try {
                retryListener.onRunning(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(retryListener, retryJob, retryContext, e));
            }
        }
    }

    /**
     * doOnCompletedInterceptors
     *
     * @param retryListeners
     * @param retryContext
     * @param retryJob
     */
    public static void doOnCompletedInterceptors(List<RetryListener> retryListeners, RetryContext retryContext, RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        if (retryContext == null) {
            return;
        }
        if (retryJob == null) {
            return;
        }
        for (RetryListener retryListener : retryListeners) {
            if (retryListener == null) {
                continue;
            }
            try {
                retryListener.onCompleted(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(retryListener, retryJob, retryContext, e));
            }
        }
    }

    /**
     * doOnErrorInterceptors
     *
     * @param retryListeners
     * @param retryContext
     * @param retryJob
     */
    public static void doOnErrorInterceptors(List<RetryListener> retryListeners,
                                             RetryContext retryContext,
                                             RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        if (retryContext == null) {
            return;
        }
        if (retryJob == null) {
            return;
        }
        for (RetryListener retryListener : retryListeners) {
            if (retryListener == null) {
                continue;
            }
            try {
                retryListener.onError(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(retryListener, retryJob, retryContext, e));
            }
        }
    }

    /**
     * doOnAbortInterceptors
     *
     * @param retryListeners
     * @param retryContext
     * @param retryJob
     */
    public static void doOnAbortInterceptors(List<RetryListener> retryListeners, RetryContext retryContext, RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryListeners)) {
            return;
        }
        if (retryContext == null) {
            return;
        }
        if (retryJob == null) {
            return;
        }
        for (RetryListener retryListener : retryListeners) {
            if (retryListener == null) {
                continue;
            }
            try {
                retryListener.onAbort(retryContext, retryJob);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e, buildLogContent(retryListener, retryJob, retryContext, e));
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