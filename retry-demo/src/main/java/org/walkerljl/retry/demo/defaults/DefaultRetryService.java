package org.walkerljl.retry.demo.defaults;

import java.util.List;

import org.walkerljl.retry.RetryService;
import org.walkerljl.retry.db.dao.ModelAndDOConverter;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryLogDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.exception.RetryException;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.logger.Logger;
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

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(RetrySupportConstants.LOGGER_DB_DIGEST);
    private static final     Logger DETAIL_LOGGER = LoggerFactory.getLogger(RetrySupportConstants.LOGGER_DB_DETAIL);

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

        InvocationInfo<String, RetryJob> invocationInfo =
                new InvocationInfo<>(getClass(), "getRetryJobById", retryJobId);
        try {
            RetryJob retryJob = ModelAndDOConverter.toRetryJob(retryJobDAO.getById(retryJobId));
            if (retryJob != null) {
                List<RetryParam> retryParams = ModelAndDOConverter.toRetryParams(retryParamDAO.listByRetryJobId(Long.parseLong(retryJob.getId())));
                retryJob.setParams(retryParams);
            }

            invocationInfo.markSuccess(retryJob);

            return retryJob;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public boolean lockRetryJob(LockRetryJobParam lockRetryJobParam) {

        InvocationInfo<LockRetryJobParam, Boolean> invocationInfo =
                new InvocationInfo<>(getClass(), "lockRetryJob", lockRetryJobParam);

        try {
            int count = retryJobDAO.lock(
                    lockRetryJobParam.getRetryJobId(),
                    lockRetryJobParam.getRetryTimeout(),
                    lockRetryJobParam.getLastRetryTime());

            boolean isLocked = count > 0;

            invocationInfo.markSuccess(isLocked);

            return isLocked;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public boolean unlockRetryJob(UnlockRetryJobParam unlockRetryJobParam) {

        InvocationInfo<UnlockRetryJobParam, Boolean> invocationInfo =
                new InvocationInfo<>(getClass(), "unlockRetryJob", unlockRetryJobParam);

        try {
            int count = retryJobDAO.unlock(unlockRetryJobParam.getRetryJobId(), unlockRetryJobParam.getStatus().getCode(),
                    unlockRetryJobParam.getNextRetryTime());

            return count > 0;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public void saveRetryLog(RetryLog retryLog) {

        InvocationInfo<RetryLog, Boolean> invocationInfo =
                new InvocationInfo<>(getClass(), "saveRetryLog", retryLog);

        try {
            retryLogDAO.save(ModelAndDOConverter.toRetryLogDO(retryLog));

            invocationInfo.markSuccess();
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<RetryJob> listUnprocessRetryJobs(long jobLoadInterval, int currentPage, int pageSize) {

        InvocationInfo<Object[], List<RetryJob>> invocationInfo =
                new InvocationInfo<>(getClass(), "listUnprocessRetryJobs",
                        new Object[]{jobLoadInterval, currentPage, pageSize});

        try {
            List<RetryJob> retryJobs = ModelAndDOConverter.toRetryJobs(
                    retryJobDAO.listUnprocessRetryJobs(
                            jobLoadInterval,
                            currentPage,
                            pageSize));

            invocationInfo.markSuccess(retryJobs);
            return retryJobs;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<RetryJob> listFailureRetryJobs(long jobLoadInterval, int currentPage, int pageSize) {

        InvocationInfo<Object[], List<RetryJob>> invocationInfo =
                new InvocationInfo<>(getClass(), "listFailureRetryJobs",
                        new Object[]{jobLoadInterval, currentPage, pageSize});

        try {
            List<RetryJob> retryJobs = ModelAndDOConverter.toRetryJobs(retryJobDAO.listFailureRetryJobs(jobLoadInterval, currentPage, pageSize));
            invocationInfo.markSuccess(retryJobs);
            return retryJobs;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<RetryJob> listTimeoutRetryJobs(long jobLoadInterval, long retryTimeout, int currentPage, int pageSize) {

        InvocationInfo<Object[], List<RetryJob>> invocationInfo =
                new InvocationInfo<>(getClass(), "listTimeoutRetryJobs",
                        new Object[]{jobLoadInterval, retryTimeout, currentPage, pageSize});

        try {
            List<RetryJob> retryJobs = ModelAndDOConverter.toRetryJobs(
                    retryJobDAO.listTimeoutRetryJobs(jobLoadInterval, retryTimeout, currentPage, pageSize));
            invocationInfo.markSuccess(retryJobs);
            return retryJobs;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }
}