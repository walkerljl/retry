package org.walkerljl.retry.db.dao.daointerface.impl;

import java.io.Serializable;

import org.walkerljl.toolkit.db.orm.executor.Executor;
import org.walkerljl.toolkit.db.orm.session.SqlSession;

/**
 *
 * @author xingxun
 */
public abstract class BaseDAOImpl<T, KEY extends Serializable> {

    protected Executor getExecutor() {
        return DbUtil.getInstance().getExecutor();
    }

    protected SqlSession<T, KEY> getSqlSession() {
        return DbUtil.getInstance().getSqlSession();
    }
}