package org.walkerljl.retry.db.dao.daointerface.impl;

import java.util.List;

import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;

/**
 *
 * @author xingxun
 */
public class RetryParamDAOImpl extends BaseDAOImpl<RetryParamDO, Long> implements RetryParamDAO {

    @Override
    public void insert(RetryParamDO retryParamDO) {
        getSqlSession().insertReturnPK(retryParamDO);
    }

    public List<RetryParamDO> selectListByRetryJobId(Long retryJobId) {
        String sql = "SELECT * FROM retry_param WHERE retry_job_id = ?";
        Object[] params = new Object[] {retryJobId};
        return getExecutor().queryEntityList(RetryParamDO.class, sql, params);
    }
}