package org.walkerljl.retry.impl.defaults;

import java.util.concurrent.BlockingQueue;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.model.RetryJob;

/**
 * 默认的远程队列
 *
 * @author xingxun
 */
public class DefaultRemoteRetryJobQueue implements RemoteRetryJobQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY);

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