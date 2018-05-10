package org.walkerljl.retry.support;

import org.walkerljl.retry.RemoteRetryJobQueue;
import org.walkerljl.retry.standard.Machine;

/**
 * Loader of retry job
 *
 *
 * @author xingxun
 */
public interface RetryJobLoader extends Machine {

    /**
     * Load retry job to RemoteRetryJobQueue
     *
     * @param remoteRetryJobQueue Remote retry job queue
     */
    void load(RemoteRetryJobQueue remoteRetryJobQueue);
}