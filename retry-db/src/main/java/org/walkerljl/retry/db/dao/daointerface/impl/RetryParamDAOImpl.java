package org.walkerljl.retry.db.dao.daointerface.impl;

import java.util.List;

import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;

/**
 * RetryParamDAOImpl
 *
 * @author xingxun
 */
public class RetryParamDAOImpl extends BaseDAOImpl<RetryParamDO, Long> implements RetryParamDAO {

    @Override
    public void save(RetryParamDO retryParamDO) {
        getSqlSession().insertReturnPK(retryParamDO);
    }

    @Override
    public List<RetryParamDO> listByRetryJobId(Long retryJobId) {
        String sql = "SELECT * FROM retry_param WHERE retry_job_id = ?";
        Object[] params = new Object[] {retryJobId};
        return getExecutor().queryEntityList(RetryParamDO.class, sql, params);
    }
}