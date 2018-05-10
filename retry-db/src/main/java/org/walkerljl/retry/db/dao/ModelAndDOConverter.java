package org.walkerljl.retry.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;
import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;
import org.walkerljl.retry.db.util.JSONUtil;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.toolkit.lang.CollectionUtils;

/**
 *
 * @author lijunlin
 */
public class ModelAndDOConverter {

    public static RetryLogDO toRetryLogDO(RetryLog retryLog) {
        if (retryLog == null) {
            return null;
        }
        RetryLogDO retryLogDO = new RetryLogDO();
        retryLogDO.setRetryJobId(retryLog.getRetryJobId());
        retryLogDO.setAttempts(retryLog.getAttempts());
        retryLogDO.setStatus(retryLog.getStatus().getCode());
        retryLogDO.setCreator(retryLog.getCreator());
        retryLogDO.setCreated(retryLog.getCreated());
        return retryLogDO;
    }

    public static List<RetryJob> toRetryJobs(List<RetryJobDO> retryJobDOs) {
        if (CollectionUtils.isEmpty(retryJobDOs)) {
            return null;
        }
        List<RetryJob> retryJobs = new ArrayList<RetryJob>(retryJobDOs.size());
        for (RetryJobDO retryJobDO : retryJobDOs) {
            retryJobs.add(toRetryJob(retryJobDO));
        }
        return retryJobs;
    }

    public static RetryJob toRetryJob(RetryJobDO retryJobDO) {
        if (retryJobDO == null) {
            return null;
        }

        RetryJob retryJob = new RetryJob();
        retryJob.setId(String.valueOf(retryJobDO.getId()));
        retryJob.setBizType(retryJobDO.getBizType());
        retryJob.setBizId(retryJobDO.getBizId());
        retryJob.setPriority(RetryPriorityEnum.getType(String.valueOf(retryJobDO.getPriority())));
        retryJob.setTargetIdentifer(retryJobDO.getTargetIdentifier());
        retryJob.setAttempts(retryJobDO.getAttempts());
        retryJob.setMaxAttempts(retryJobDO.getMaxAttempts());
        retryJob.setLastRetryTime(retryJobDO.getLastRetryTime());
        retryJob.setNextRetryTime(retryJobDO.getNextRetryTime());
        retryJob.setRetryRule(retryJobDO.getRetryRule());
        retryJob.setExtInfo(JSONUtil.parseMap(retryJobDO.getExtInfo()));
        retryJob.setRemark(retryJobDO.getRemark());
        retryJob.setStatus(RetryJobStatusEnum.getType(retryJobDO.getStatus()));
        retryJob.setCreator(retryJobDO.getCreator());
        retryJob.setCreated(retryJobDO.getCreated());
        retryJob.setModifier(retryJobDO.getModifier());
        retryJob.setModified(retryJobDO.getModified());

        return retryJob;
    }

    public static RetryJobDO toRetryJobDO(RetryJob retryJob) {
        if (retryJob == null) {
            return null;
        }

        RetryJobDO retryJobDO = new RetryJobDO();
        retryJobDO.setBizType(retryJob.getBizType());
        retryJobDO.setBizId(retryJob.getBizId());
        retryJobDO.setPriority(Integer.parseInt(retryJob.getPriority().getCode()));
        retryJobDO.setTargetIdentifier(retryJob.getTargetIdentifer());
        retryJobDO.setAttempts(retryJob.getAttempts());
        retryJobDO.setMaxAttempts(retryJob.getMaxAttempts());
        retryJobDO.setLastRetryTime(retryJob.getLastRetryTime());
        retryJobDO.setNextRetryTime(retryJob.getNextRetryTime());
        retryJobDO.setRetryRule(retryJob.getRetryRule());
        retryJobDO.setExtInfo(JSONUtil.toJSONString(retryJobDO.getExtInfo()));
        retryJobDO.setRemark(retryJob.getRemark());
        retryJobDO.setStatus(retryJob.getStatus().getCode());
        retryJobDO.setCreator(retryJob.getCreator());
        retryJobDO.setCreated(retryJob.getCreated());
        retryJobDO.setModifier(retryJob.getModifier());
        retryJobDO.setModified(retryJob.getModified());

        return retryJobDO;
    }

    public static RetryParam toRetryParam(RetryParamDO retryParamDO) {
        if (retryParamDO == null) {
            return null;
        }

        RetryParam retryParam = new RetryParam();
        retryParam.setRetryJobId(retryParamDO.getRetryJobId());
        retryParam.setValue(retryParamDO.getValue());
        retryParam.setRemark(retryParamDO.getRemark());
        retryParam.setStatus(RetryParamStatusEnum.getType(retryParamDO.getStatus()));
        retryParam.setCreator(retryParamDO.getCreator());
        retryParam.setCreated(retryParamDO.getCreated());
        retryParam.setModifier(retryParamDO.getModifier());
        retryParam.setModified(retryParamDO.getModified());
        return retryParam;
    }

    public static List<RetryParam> toRetryParams(List<RetryParamDO> retryParamDOList) {
        if (CollectionUtils.isEmpty(retryParamDOList)) {
            return null;
        }
        List<RetryParam> retryParams = new ArrayList<RetryParam>(retryParamDOList.size());
        for (RetryParamDO retryParamDO : retryParamDOList) {
            retryParams.add(toRetryParam(retryParamDO));
        }
        return retryParams;
    }

    public static RetryParamDO toRetryParamDO(RetryParam retryParam) {
        if (retryParam == null) {
            return null;
        }

        RetryParamDO retryParamDO = new RetryParamDO();
        retryParamDO.setRetryJobId(retryParam.getRetryJobId());
        retryParamDO.setValue(JSONUtil.toJSONString(retryParam.getValue()));
        retryParamDO.setRemark(retryParam.getRemark());
        retryParamDO.setStatus(retryParam.getStatus().getCode());
        retryParamDO.setCreator(retryParam.getCreator());
        retryParamDO.setCreated(retryParam.getCreated());
        retryParamDO.setModifier(retryParam.getModifier());
        retryParamDO.setModified(retryParam.getModified());
        return retryParamDO;
    }
}