package org.walkerljl.retry.demo.defaults;

import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.abstracts.AbstractRetryBroker;
import org.walkerljl.retry.db.dao.ModelAndDOConverter;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.exception.RetryException;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.impl.util.CollectionUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryParam;

/**
 * DefaultRetryBroker
 *
 * @author xingxun
 */
public class DefaultRetryBroker extends AbstractRetryBroker implements RetryBroker {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(RetrySupportConstants.LOGGER_DB_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(RetrySupportConstants.LOGGER_DB_DETAIL);

    private RetryJobDAO   retryJobDAO;
    private RetryParamDAO retryParamDAO;

    public DefaultRetryBroker(RetryJobDAO retryJobDAO, RetryParamDAO retryParamDAO) {
        this.retryJobDAO = retryJobDAO;
        this.retryParamDAO = retryParamDAO;
    }

    @Override
    public String processSubmit(RetryJob retryJob) {

        InvocationInfo<RetryJob, String> invocationInfo =
                new InvocationInfo<>(getClass(), "processSubmit", retryJob);
        try {
            String retryJobId = null;
            if (CollectionUtil.isEmpty(retryJob.getParams())) {
                RetryJobDO retryJobDO = ModelAndDOConverter.toRetryJobDO(retryJob);
                if (retryJobDO == null) {
                    return null;
                }
                retryJobDAO.save(retryJobDO);
                retryJobId = String.valueOf(retryJobDO.getId());
            } else {
                RetryJobDO retryJobDO = ModelAndDOConverter.toRetryJobDO(retryJob);
                if (retryJobDO == null) {
                    return null;
                }
                retryJobDAO.save(retryJobDO);
                for (RetryParam retryParam : retryJob.getParams()) {
                    if (retryParam == null) {
                        continue;
                    }
                    retryParam.setRetryJobId(String.valueOf(retryJobDO.getId()));
                    retryParamDAO.save(ModelAndDOConverter.toRetryParamDO(retryParam));
                }
                retryJobId = String.valueOf(retryJobDO.getId());
            }

            invocationInfo.markSuccess(retryJobId);
            return retryJobId;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public void processMarkRetryJobToCompleted(String retryJobId) {

        InvocationInfo<String, Void> invocationInfo =
                new InvocationInfo<>(getClass(), "processMarkRetryJobToCompleted", retryJobId);
        try {
            retryJobDAO.markToCompleted(Long.valueOf(retryJobId));
            invocationInfo.markSuccess();
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new RetryException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }
}