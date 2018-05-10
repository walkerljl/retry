package org.walkerljl.retry.demo.impl.defaults;

import org.walkerljl.retry.RetryWaiter;
import org.walkerljl.retry.db.dao.ModelAndDOConverter;
import org.walkerljl.retry.db.dao.daointerface.RetryJobDAO;
import org.walkerljl.retry.db.dao.daointerface.RetryParamDAO;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.toolkit.lang.CollectionUtils;

/**
 * DefaultRetryWaiter
 *
 * @author xingxun
 */
public class DefaultRetryWaiter implements RetryWaiter {

    private RetryJobDAO   retryJobDAO;
    private RetryParamDAO retryParamDAO;

    public DefaultRetryWaiter(RetryJobDAO retryJobDAO, RetryParamDAO retryParamDAO) {
        this.retryJobDAO = retryJobDAO;
        this.retryParamDAO = retryParamDAO;
    }

    @Override
    public void submit(RetryJob retryJob) {
        if (CollectionUtils.isEmpty(retryJob.getParams())) {
            retryJobDAO.insert(ModelAndDOConverter.toRetryJobDO(retryJob));
        } else {
           RetryJobDO retryJobDO = ModelAndDOConverter.toRetryJobDO(retryJob);
            retryJobDAO.insert(retryJobDO);
            for (RetryParam retryParam : retryJob.getParams()) {
                retryParam.setRetryJobId(String.valueOf(retryJobDO.getId()));
                retryParamDAO.insert(ModelAndDOConverter.toRetryParamDO(retryParam));
            }
        }
    }
}