package org.walkerljl.retry.db.dao.daointerface.impl;

import java.util.Date;
import java.util.List;

import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;

/**
 * RetryJobDAOImpl
 *
 * @author xingxun
 */
public class RetryJobDAOImpl extends BaseDAOImpl<RetryJobDO, Long> implements RetryJobDAO {

    @Override
    public void save(RetryJobDO retryJobDO) {
        Long id = getSqlSession().insertReturnPK(retryJobDO);
        retryJobDO.setId(id);
    }

    @Override
    public int markToCompleted(Long id) {
        /*
        UPDATE retry_job SET status = 'processed', gmt_modified = NOW() WHERE id = 1133;
         */
        String sql = "UPDATE retry_job SET status = 'processed', gmt_modified = NOW() WHERE id = ?";
        Object[] params = new Object[] {
                id
        };
        return getExecutor().update(sql, params);
    }

    @Override
    public int lock(String id, Long retryTimeout, Date lastRetryTime) {
        /*
        UPDATE
            retry_job
        SET
            status = 'processing', attempts = attempts + 1, last_retry_time = NOW(), gmt_modified = NOW()
        WHERE
            id = 1133
        AND
            (status != 'processing' OR (status = 'processing' AND DATE_ADD(last_retry_time, INTERVAL 60 SECOND) <= NOW()));
         */
        String sql = "UPDATE retry_job SET status = 'processing', attempts = attempts + 1, last_retry_time = ?, gmt_modified = NOW() "
                + " WHERE id = ? AND (status != 'processing'"
                + " OR (status = 'processing' AND DATE_ADD(last_retry_time, INTERVAL ? SECOND) <= NOW()))";
        Object[] params = new Object[] {
                lastRetryTime,
                id,
                retryTimeout
        };
        return getExecutor().update(sql, params);
    }

    @Override
    public int unlock(String id, String status, Date nextRetryTime) {
        /*
        UPDATE retry_job SET status = 'processed', next_retry_time = NOW(), gmt_modified = NOW() WHERE id = 1133;
         */
        String sql = "UPDATE retry_job SET status = ?, next_retry_time = ?, gmt_modified = NOW() WHERE id = ?";
        Object[] params = new Object[] {status,
                nextRetryTime,
                id
        };
        return getExecutor().update(sql, params);
    }

    @Override
    public RetryJobDO getById(String id) {
        String sql = "SELECT * FROM retry_job WHERE id = ?";
        Object[] params = new Object[] {id};
        return getExecutor().queryEntity(RetryJobDO.class, sql, params);
    }

    @Override
    public List<RetryJobDO> listUnprocessRetryJobs(Long loadInterval, Integer currentPage, Integer pageSize) {
        /*
        SELECT
            id,biz_type,biz_id,priority,target_identifier,attempts,max_attempts,retry_rule,last_retry_time,next_retry_time,ext_info,
                remark,status,creator,gmt_create,modifier,gmt_modified
        FROM
            retry_job
        WHERE
            status = 'unprocess'
        AND
            attempts < max_attempts
        AND
            next_retry_time <= DATE_ADD(NOW(), INTERVAL 60 SECOND)
        ORDER BY
            priority ASC, gmt_create ASC
        LIMIT
            1, 10;
         */
        String sql
                = "SELECT * FROM retry_job WHERE status = 'unprocess' "
                + " AND attempts < max_attempts "
                + " AND next_retry_time <= DATE_ADD(NOW(), INTERVAL ? SECOND)"
                + " ORDER BY priority ASC, gmt_create ASC "
                + " LIMIT ?, ?";
        Object[] params = new Object[] {loadInterval, (currentPage - 1) * pageSize, pageSize};
        return getExecutor().queryEntityList(RetryJobDO.class, sql, params);
    }

    @Override
    public List<RetryJobDO> listFailureRetryJobs(Long loadInterval, Integer currentPage, Integer pageSize) {
        /*
        SELECT
            id,biz_type,biz_id,priority,target_identifier,attempts,max_attempts,retry_rule,last_retry_time,next_retry_time,ext_info,
                remark,status,creator,gmt_create,modifier,gmt_modified
        FROM
            retry_job
        WHERE
            status = 'failure'
        AND
            attempts < max_attempts
        AND
            next_retry_time <= DATE_ADD(NOW(), INTERVAL 60 SECOND)
        ORDER BY
            priority ASC, gmt_create ASC
        LIMIT
            1, 10;
         */
        String sql
                = "SELECT * FROM retry_job WHERE status = 'failure' "
                + " AND attempts < max_attempts "
                + " AND next_retry_time <= DATE_ADD(NOW(), INTERVAL ? SECOND) "
                + " ORDER BY priority ASC, gmt_create ASC "
                + " LIMIT ?, ?";
        Object[] params = new Object[] {loadInterval, (currentPage - 1) * pageSize, pageSize};
        return getExecutor().queryEntityList(RetryJobDO.class, sql, params);
    }

    @Override
    public List<RetryJobDO> listTimeoutRetryJobs(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
        /*
        SELECT
            id,biz_type,biz_id,priority,target_identifier,attempts,max_attempts,retry_rule,last_retry_time,next_retry_time,ext_info,
                remark,status,creator,gmt_create,modifier,gmt_modified
        FROM
            retry_job
        WHERE
            status = 'processing'
        AND
            attempts < max_attempts
        AND
            next_retry_time <= DATE_ADD(NOW(), INTERVAL 60 SECOND)
        AND
            DATE_ADD(last_retry_time, INTERVAL 120 SECOND) <= NOW()
        ORDER BY
            priority ASC, gmt_create ASC
        LIMIT
            1, 10;
         */
        String sql = "SELECT * FROM retry_job "
                + " WHERE status = 'processing' "
                + " AND attempts < max_attempts "
                + " AND next_retry_time <= DATE_ADD(NOW(), INTERVAL ? SECOND) "
                + " AND DATE_ADD(last_retry_time, INTERVAL ? SECOND) <= NOW() "
                + " ORDER BY priority ASC, gmt_create ASC "
                + " LIMIT ?, ?";
        Object[] params = new Object[] {loadInterval, retryTimeout, (currentPage - 1) * pageSize, pageSize};
        return getExecutor().queryEntityList(RetryJobDO.class, sql, params);
    }
}