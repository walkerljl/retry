package org.walkerljl.retry.db.dao.daointerface;

import java.util.Date;
import java.util.List;

import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;

/**
 * 重试任务数据访问层接口
 *
 * @author xingxun
 */
public interface RetryJobDAO {

    /**
     * 添加重试任务
     *
     * @param retryJob 重试任务
     */
    void save(RetryJobDO retryJob);

    /**
     * 标注重试任务已经完成
     *
     * @param id 重试任务ID
     */
    int markToCompleted(Long id);

    /**
     * 锁定重试任务
     *
     * @param id 重试任务ID
     * @param retryTimeout 重试超时时间
     * @param lastRetryTime 上次重试时间
     * @return
     */
    int lock(String id, Long retryTimeout, Date lastRetryTime);

    /**
     * 解锁重试任务
     *
     * @param id 重试任务ID
     * @param status 重试任务状态
     * @param nextRetryTime 下次重试时间
     */
    int unlock(String id, String status, Date nextRetryTime);

    /**
     * 根据ID查询重试任务
     *
     * @param id 重试任务ID
     * @return
     */
    RetryJobDO getById(String id);

    /**
     * 分页查询未处理的重试任务
     *
     * @param loadInterval 加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<RetryJobDO> listUnprocessRetryJobs(Long loadInterval, Integer currentPage, Integer pageSize);

    /**
     * 分页查询处理失败的重试任务
     *
     * @param loadInterval 加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<RetryJobDO> listFailureRetryJobs(Long loadInterval, Integer currentPage, Integer pageSize);

    /**
     * 分页查询超时的重试任务
     *
     * @param loadInterval 加载间隔
     * @param retryTimeout 重试超时时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<RetryJobDO> listTimeoutRetryJobs(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize);
}