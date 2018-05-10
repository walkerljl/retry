package org.walkerljl.retry.db.dao.daointerface.impl;

import org.walkerljl.retry.db.dao.daointerface.RetryLogDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;

/**
 *
 * @author lijunlin
 */
public class RetryLogDAOImpl extends BaseDAOImpl<RetryLogDO, Long> implements RetryLogDAO {

    public void insert(RetryLogDO retryLog) {
        getSqlSession().insertReturnPK(retryLog);
    }
}