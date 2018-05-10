package org.walkerljl.retry.db.dao.daointerface;

import java.util.List;

import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.ReleaseRetryJobParam;

/**
 *
 * @author lijunlin
 */
public interface RetryJobDAO {

    void insert(RetryJobDO retryJobDO);

    RetryJobDO selectByBizTypeAndBizId(String bizType, String bizId);

    int lock(LockRetryJobParam lockRetryJobParam);

    void unlock(ReleaseRetryJobParam releaseRetryJobParam);

    List<RetryJobDO> selectUnprocessRetryJobsByPage(long loadInterval, int page, int pageSize);

    List<RetryJobDO> selectFailureRetryJobsByPage(long loadInterval, int page, int pageSize);

    List<RetryJobDO> selectTimeoutRetryJobsByPage(long loadInterval, long retryTimeout, int page, int pageSize);
}