package org.walkerljl.retry.demo.impl.defaults;

import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.db.dao.ModelAndDOConverter;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.impl.util.CollectionUtil;

/**
 * DefaultRetryBroker
 *
 * @author xingxun
 */
public class DefaultRetryBroker implements RetryBroker {

    private RetryJobDAO   retryJobDAO;
    private RetryParamDAO retryParamDAO;

    public DefaultRetryBroker(RetryJobDAO retryJobDAO, RetryParamDAO retryParamDAO) {
        this.retryJobDAO = retryJobDAO;
        this.retryParamDAO = retryParamDAO;
    }

    @Override
    public void submit(RetryJob retryJob) {
        if (CollectionUtil.isEmpty(retryJob.getParams())) {
            retryJobDAO.save(ModelAndDOConverter.toRetryJobDO(retryJob));
        } else {
           RetryJobDO retryJobDO = ModelAndDOConverter.toRetryJobDO(retryJob);
           retryJobDAO.save(retryJobDO);
           for (RetryParam retryParam : retryJob.getParams()) {
               retryParam.setRetryJobId(String.valueOf(retryJobDO.getId()));
               retryParamDAO.save(ModelAndDOConverter.toRetryParamDO(retryParam));
           }
        }
    }
}