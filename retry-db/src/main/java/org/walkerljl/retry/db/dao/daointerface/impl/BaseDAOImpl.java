package org.walkerljl.retry.db.dao.daointerface.impl;

import org.walkerljl.toolkit.db.orm.executor.Executor;
import org.walkerljl.toolkit.db.orm.session.SqlSession;

import java.io.Serializable;

/**
 *
 * @author lijunlin
 */
public abstract class BaseDAOImpl<T, KEY extends Serializable> {

    protected Executor getExecutor() {
        return DbUtil.getInstance().getExecutor();
    }

    protected SqlSession<T, KEY> getSqlSession() {
        return DbUtil.getInstance().getSqlSession();
    }
}