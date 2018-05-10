package org.walkerljl.retry.db.dao.daointerface;

import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;

/**
 *
 * @author junlin.ljl
 * @version $Id: RetryLogDAO.java, v 0.1 2017年09月09日 下午12:14 junlin.ljl Exp $
 */
public interface RetryLogDAO {

    void insert(RetryLogDO retryLog);
}