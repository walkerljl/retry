package org.walkerljl.retry.db.dao.daointerface.impl;

import java.util.List;

import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.ReleaseRetryJobParam;

/**
 *
 * @author xingxun
 */
public class RetryJobDAOImpl extends BaseDAOImpl<RetryJobDO, Long> implements RetryJobDAO {

    public void insert(RetryJobDO retryJobDO) {
        Long id = getSqlSession().insertReturnPK(retryJobDO);
        retryJobDO.setId(id);
    }

    @Override
    public RetryJobDO selectByBizTypeAndBizId(String bizType, String bizId) {
        String sql = "SELECT * FROM retry_job WHERE biz_id = ? and biz_type = ?";
        Object[] params = new Object[] {bizId, bizType};
        return getExecutor().queryEntity(RetryJobDO.class, sql, params);
    }

    public int lock(LockRetryJobParam lockRetryJobParam) {
        String sql = "UPDATE retry_job SET status = 'processing', last_retry_time = ?, gmt_modified = ? "
                + "WHERE id = ? AND status != 'processing'";
        Object[] params = new Object[] {lockRetryJobParam.getLastRetryTime(),
                lockRetryJobParam.getModified(),
                lockRetryJobParam.getRetryJobId()};
        return getExecutor().update(sql, params);
    }

    public void unlock(ReleaseRetryJobParam releaseRetryJobParam) {
        String sql = "UPDATE retry_job SET status = ?, attempts = ?, next_retry_time = ?, gmt_modified = ?"
                + " WHERE id = ?";
        Object[] params = new Object[] {releaseRetryJobParam.getStatus().getCode(),
                releaseRetryJobParam.getAttempts(),
                releaseRetryJobParam.getNextRetryTime(),
                releaseRetryJobParam.getModified(),
                releaseRetryJobParam.getRetryJobId()};
        getExecutor().update(sql, params);
    }

    public List<RetryJobDO> selectUnprocessRetryJobsByPage(long loadInterval, int page, int pageSize) {
        String sql
                = "SELECT * FROM retry_job WHERE status = 'unprocess' "
                + " AND attempts <= max_attempts "
                + " AND next_retry_time <= DATE_ADD(NOW(), INTERVAL ? SECOND)"
                + " ORDER BY priority ASC,gmt_create ASC "
                + " LIMIT ?, ?";
        Object[] params = new Object[] {loadInterval, (page - 1) * pageSize, pageSize};
        return getExecutor().queryEntityList(RetryJobDO.class, sql, params);
    }

    public List<RetryJobDO> selectFailureRetryJobsByPage(long loadInterval, int page, int pageSize) {
        String sql
                = "SELECT * FROM retry_job WHERE status = 'failure' "
                + " AND attempts <= max_attempts "
                + " AND next_retry_time <= DATE_ADD(NOW(), INTERVAL ? SECOND) "
                + " ORDER BY priority ASC, gmt_create ASC "
                + " LIMIT ?, ?";
        Object[] params = new Object[] {loadInterval, (page - 1) * pageSize, pageSize};
        return getExecutor().queryEntityList(RetryJobDO.class, sql, params);
    }

    public List<RetryJobDO> selectTimeoutRetryJobsByPage(long loadInterval, long jobRetryTimeout, int page, int pageSize) {
        String sql = "SELECT * FROM retry_job "
                + " WHERE status = 'processing' "
                + " AND attempts <= max_attempts "
                + " AND next_retry_time <= DATE_ADD(NOW(), INTERVAL ? SECOND) "
                + " AND DATE_ADD(last_retry_time, INTERVAL ? SECOND) <= NOW() "
                + " ORDER BY priority ASC, gmt_create ASC "
                + " LIMIT ?, ?";
        Object[] params = new Object[] {loadInterval, jobRetryTimeout, (page - 1) * pageSize, pageSize};
        return getExecutor().queryEntityList(RetryJobDO.class, sql, params);
    }
}