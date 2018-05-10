package org.walkerljl.retry.db.dao.daointerface.impl;

import org.walkerljl.toolkit.db.orm.executor.Executor;
import org.walkerljl.toolkit.db.orm.executor.defaults.DefaultExecutor;
import org.walkerljl.toolkit.db.orm.session.Configuration;
import org.walkerljl.toolkit.db.orm.session.SqlSession;
import org.walkerljl.toolkit.db.orm.session.defaults.DefaultSqlSession;

/**
 *
 * @author lijunlin
 */
public class DbUtil {

    private Configuration configuration;
    private boolean isInited = false;
    private Executor   executor;
    private SqlSession sqlSession;

    private DbUtil() {}

    public void bindConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void init() {
        synchronized (this) {
            if (!isInited) {
                executor = new DefaultExecutor(configuration.getDataSource());
                sqlSession = new DefaultSqlSession(configuration);
                isInited = true;
            }
        }
    }

    /**
     * Getter method for property <tt>executor</tt>.
     *
     * @return property value of executor
     */
    public Executor getExecutor() {
        return executor;
    }

    /**
     * Getter method for property <tt>sqlSession</tt>.
     *
     * @return property value of sqlSession
     */
    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public static DbUtil getInstance() {
        return DbUtilHolder.instance;
    }

    private static class DbUtilHolder {
        private static DbUtil instance = new DbUtil();
    }
}