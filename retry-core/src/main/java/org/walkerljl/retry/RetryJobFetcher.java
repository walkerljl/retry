package org.walkerljl.retry;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.Machine;

/**
 * RetryJobFetcher
 *
 * @author xingxun
 */
public interface RetryJobFetcher extends Machine {

    void onFetch(RetryJob retryJob);
}