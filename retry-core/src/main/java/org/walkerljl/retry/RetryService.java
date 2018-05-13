package org.walkerljl.retry;

import java.util.List;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.UnlockRetryJobParam;

/**
 * 重试业务接口
 *
 * @author xingxun
 */
public interface RetryService {

    /**
     * 根据重试任务ID查询重试任务
     *
     * @param retryJobId 重试任务ID
     * @return
     */
    RetryJob getRetryJobById(String retryJobId);

    /**
     * 锁定重试任务
     *
     * @param lockRetryJobParam 锁定重试任务参数
     * @return
     */
    boolean lockRetryJob(LockRetryJobParam lockRetryJobParam);

    /**
     * 解锁重试任务
     *
     * @param unlockRetryJobParam 解锁重试任务参数
     */
    boolean unlockRetryJob(UnlockRetryJobParam unlockRetryJobParam);

    /**
     * 添加重试日志
     *
     * @param retryLog 重试日志
     */
    void saveRetryLog(RetryLog retryLog);

    /**
     * 分页查询未处理的重试任务列表
     *
     * @param jobLoadInterval 任务加载间隔时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<RetryJob> listUnprocessRetryJobs(long jobLoadInterval, int currentPage, int pageSize);

    /**
     * 分页查询处理失败的重试任务列表
     *
     * @param jobLoadInterval 任务加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<RetryJob> listFailureRetryJobs(long jobLoadInterval, int currentPage, int pageSize);

    /**
     * 分页查询处理超时的重试任务列表
     *
     * @param jobLoadInterval 任务加载间隔
     * @param retryTimeout 重试超时时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<RetryJob> listTimeoutRetryJobs(long jobLoadInterval, long retryTimeout, int currentPage, int pageSize);
}