package org.walkerljl.retry.db.dao.daointerface;

import java.util.List;

import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;

/**
 *
 * @author xingxun
 */
public interface RetryParamDAO {

    void insert(RetryParamDO retryParamDO);

    List<RetryParamDO> selectListByRetryJobId(Long retryJobId);
}