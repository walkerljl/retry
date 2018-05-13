package org.walkerljl.retry.db.dao.daointerface.impl;

import org.walkerljl.retry.db.dao.daointerface.RetryLogDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;

/**
 * RetryLogDAOImpl
 *
 * @author xingxun
 */
public class RetryLogDAOImpl extends BaseDAOImpl<RetryLogDO, Long> implements RetryLogDAO {

    @Override
    public void save(RetryLogDO retryLog) {
        getSqlSession().insertReturnPK(retryLog);
    }
}