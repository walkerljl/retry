package org.walkerljl.retry.demo.impl.defaults;

import java.util.List;

import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.db.dao.ModelAndDOConverter;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryLogDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.ReleaseRetryJobParam;

/**
 *
 * @author lijunlin
 */
public class DefaultRetryService implements RetryService {

    private RetryJobDAO retryJobDAO;
    private RetryParamDAO retryParamDAO;
    private RetryLogDAO retryLogDAO;

    public DefaultRetryService(RetryJobDAO retryJobDAO, RetryParamDAO retryParamDAO,
                               RetryLogDAO retryLogDAO) {
        this.retryJobDAO = retryJobDAO;
        this.retryParamDAO = retryParamDAO;
        this.retryLogDAO = retryLogDAO;
    }

    @Override
    public RetryJob getRetryJob(String bizType, String bizId) {
        RetryJob retryJob = ModelAndDOConverter.toRetryJob(retryJobDAO.selectByBizTypeAndBizId(bizType, bizId));
        if (retryJob == null) {
            return null;
        }
        Long retryJobId = Long.parseLong(retryJob.getId());
        List<RetryParam> retryParams = ModelAndDOConverter.toRetryParams(retryParamDAO.selectListByRetryJobId(retryJobId));
        retryJob.setParams(retryParams);

        return retryJob;
    }

    @Override
    public int lockRetryJob(LockRetryJobParam lockRetryJobParam) {
        return retryJobDAO.lock(lockRetryJobParam);
    }

    @Override
    public void unlockRetryJob(ReleaseRetryJobParam releaseRetryJobParam) {
        retryJobDAO.unlock(releaseRetryJobParam);
    }

    @Override
    public void recordRetryLog(RetryLog retryLog) {
        retryLogDAO.insert(ModelAndDOConverter.toRetryLogDO(retryLog));
    }

    @Override
    public List<RetryJob> listUnprocessRetryJobsByPage(long jobLoadInterval, int currentPage, int pageSize) {
        return ModelAndDOConverter.toRetryJobs(retryJobDAO.selectUnprocessRetryJobsByPage(jobLoadInterval, currentPage, pageSize));
    }

    @Override
    public List<RetryJob> listFailureRetryJobsByPage(long jobLoadInterval, int currentPage, int pageSize) {
        return ModelAndDOConverter.toRetryJobs(retryJobDAO.selectFailureRetryJobsByPage(jobLoadInterval, currentPage, pageSize));
    }

    @Override
    public List<RetryJob> listTimeoutRetryJobsByPage(long jobLoadInterval, long retryTimeout, int currentPage, int pageSize) {
        return ModelAndDOConverter.toRetryJobs(retryJobDAO.selectTimeoutRetryJobsByPage(jobLoadInterval, retryTimeout, currentPage, pageSize));
    }
}