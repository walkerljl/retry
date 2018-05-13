package org.walkerljl.retry.db.dao.daointerface;

import java.util.List;

import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;

/**
 * 重试任务参数数据访问层接口
 *
 * @author xingxun
 */
public interface RetryParamDAO {

    /**
     * 添加重试任务参数
     *
     * @param retryParam 重试任务参数
     */
    void save(RetryParamDO retryParam);

    /**
     * 根据重试任务ID查询参数列表
     *
     * @param retryJobId 重试任务ID
     * @return
     */
    List<RetryParamDO> listByRetryJobId(Long retryJobId);
}