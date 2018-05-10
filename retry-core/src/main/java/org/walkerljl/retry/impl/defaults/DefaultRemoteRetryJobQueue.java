package org.walkerljl.retry.impl.defaults;

import java.util.concurrent.BlockingQueue;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.log.util.LoggerUtil;
import org.walkerljl.retry.model.RetryJob;

/**
 * DefaultRemoteRetryJobQueue
 *
 * @author xingxun
 */
public class DefaultRemoteRetryJobQueue implements RemoteRetryJobQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRemoteRetryJobQueue.class);

    private BlockingQueue<RetryJob> retryJobQueue;

    public DefaultRemoteRetryJobQueue(BlockingQueue<RetryJob> retryJobQueue) {
        this.retryJobQueue = retryJobQueue;
    }

    @Override
    public void addRetryJob(RetryJob retryJob) {
        if (LOGGER.isInfoEnabled()) {
            LoggerUtil.info(LOGGER, String.format("Add one retry job to RemoteRetryJobQueue:%s.",
                    String.valueOf(retryJob)));
        }
        if (retryJob == null) {
            return;
        }
        retryJobQueue.add(retryJob);
    }
}