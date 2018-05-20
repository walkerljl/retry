package org.walkerljl.retry.demo.defaults;

import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.abstracts.AbstractRetryBroker;
import org.walkerljl.retry.db.dao.ModelAndDOConverter;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.impl.util.CollectionUtil;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryParam;

/**
 * DefaultRetryBroker
 *
 * @author xingxun
 */
public class DefaultRetryBroker extends AbstractRetryBroker implements RetryBroker {

    private RetryJobDAO   retryJobDAO;
    private RetryParamDAO retryParamDAO;

    public DefaultRetryBroker(RetryJobDAO retryJobDAO, RetryParamDAO retryParamDAO) {
        this.retryJobDAO = retryJobDAO;
        this.retryParamDAO = retryParamDAO;
    }

    @Override
    public String processSubmit(RetryJob retryJob) {

        if (CollectionUtil.isEmpty(retryJob.getParams())) {
            RetryJobDO retryJobDO = ModelAndDOConverter.toRetryJobDO(retryJob);
            if (retryJobDO == null) {
                return null;
            }
            retryJobDAO.save(retryJobDO);
            return String.valueOf(retryJobDO.getId());
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
            return String.valueOf(retryJobDO.getId());
        }
    }

    @Override
    public void processMarkRetryJobToCompleted(String retryJobId) {

        retryJobDAO.markToCompleted(Long.valueOf(retryJobId));
    }
}