package org.walkerljl.retry.db.dao.daointerface;

import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;

/**
 * 重试日志数据访问层接口
 *
 * @author xingxun
 */
public interface RetryLogDAO {

    /**
     * 添加重试日志
     *
     * @param retryLog 重试日志
     */
    void save(RetryLogDO retryLog);
}