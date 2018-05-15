package org.walkerljl.retry.demo.defaults;

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
import org.walkerljl.retry.model.param.UnlockRetryJobParam;

/**
 * 默认的重试业务接口
 *
 * @author xingxun
 */
public class DefaultRetryService implements RetryService {

    private RetryJobDAO   retryJobDAO;
    private RetryParamDAO retryParamDAO;
    private RetryLogDAO   retryLogDAO;

    public DefaultRetryService(RetryJobDAO retryJobDAO, RetryParamDAO retryParamDAO,
                               RetryLogDAO retryLogDAO) {
        this.retryJobDAO = retryJobDAO;
        this.retryParamDAO = retryParamDAO;
        this.retryLogDAO = retryLogDAO;
    }

    @Override
    public RetryJob getRetryJobById(String retryJobId) {
        RetryJob retryJob = ModelAndDOConverter.toRetryJob(retryJobDAO.getById(retryJobId));
        if (retryJob == null) {
            return null;
        }
        List<RetryParam> retryParams = ModelAndDOConverter.toRetryParams(retryParamDAO.listByRetryJobId(Long.parseLong(retryJob.getId())));
        retryJob.setParams(retryParams);

        return retryJob;
    }

    @Override
    public boolean lockRetryJob(LockRetryJobParam lockRetryJobParam) {
        int count = retryJobDAO.lock(lockRetryJobParam.getRetryJobId(),
                lockRetryJobParam.getRetryTimeout(),
                lockRetryJobParam.getLastRetryTime());
        return count > 0;
    }

    @Override
    public boolean unlockRetryJob(UnlockRetryJobParam unlockRetryJobParam) {
        int count = retryJobDAO.unlock(unlockRetryJobParam.getRetryJobId(), unlockRetryJobParam.getStatus().getCode(),
                unlockRetryJobParam.getNextRetryTime());
        return count > 0;
    }

    @Override
    public void saveRetryLog(RetryLog retryLog) {
        retryLogDAO.save(ModelAndDOConverter.toRetryLogDO(retryLog));
    }

    @Override
    public List<RetryJob> listUnprocessRetryJobs(long jobLoadInterval, int currentPage, int pageSize) {
        return ModelAndDOConverter.toRetryJobs(retryJobDAO.listUnprocessRetryJobs(jobLoadInterval, currentPage, pageSize));
    }

    @Override
    public List<RetryJob> listFailureRetryJobs(long jobLoadInterval, int currentPage, int pageSize) {
        return ModelAndDOConverter.toRetryJobs(retryJobDAO.listFailureRetryJobs(jobLoadInterval, currentPage, pageSize));
    }

    @Override
    public List<RetryJob> listTimeoutRetryJobs(long jobLoadInterval, long retryTimeout, int currentPage, int pageSize) {
        return ModelAndDOConverter.toRetryJobs(retryJobDAO.listTimeoutRetryJobs(jobLoadInterval, retryTimeout, currentPage, pageSize));
    }
}