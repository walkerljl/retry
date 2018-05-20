package org.walkerljl.retry.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;
import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;
import org.walkerljl.retry.db.util.JSONUtil;
import org.walkerljl.retry.impl.util.CollectionUtil;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;

/**
 * 核型领域模型、DO转换器
 *
 * @author xingxun
 */
public class ModelAndDOConverter {

    /**
     * toRetryLogDO
     *
     * @param retryLog
     * @return
     */
    public static RetryLogDO toRetryLogDO(RetryLog retryLog) {
        if (retryLog == null) {
            return null;
        }
        RetryLogDO retryLogDO = new RetryLogDO();
        retryLogDO.setRetryJobId(retryLog.getRetryJobId());
        retryLogDO.setAttempts(retryLog.getAttempts());
        retryLogDO.setDescription(retryLog.getDescription());
        retryLogDO.setStatus(retryLog.getStatus().getCode());
        retryLogDO.setRemark(retryLog.getRemark());
        retryLogDO.setCreator(retryLog.getCreator());
        retryLogDO.setCreatedTime(retryLog.getCreatedTime());
        return retryLogDO;
    }

    /**
     * toRetryJobs
     *
     * @param retryJobDOs
     * @return
     */
    public static List<RetryJob> toRetryJobs(List<RetryJobDO> retryJobDOs) {
        if (CollectionUtil.isEmpty(retryJobDOs)) {
            return null;
        }
        List<RetryJob> retryJobs = new ArrayList<>(retryJobDOs.size());
        for (RetryJobDO retryJobDO : retryJobDOs) {
            RetryJob retryJob = toRetryJob(retryJobDO);
            if (retryJob != null) {
                retryJobs.add(retryJob);
            }
        }
        return retryJobs;
    }

    /**
     * toRetryJob
     *
     * @param retryJobDO
     * @return
     */
    public static RetryJob toRetryJob(RetryJobDO retryJobDO) {
        if (retryJobDO == null) {
            return null;
        }

        RetryJob retryJob = new RetryJob();
        retryJob.setId(String.valueOf(retryJobDO.getId()));
        retryJob.setBizType(retryJobDO.getBizType());
        retryJob.setBizId(retryJobDO.getBizId());
        retryJob.setPriority(RetryPriorityEnum.getType(String.valueOf(retryJobDO.getPriority())));
        retryJob.setTargetIdentifier(retryJobDO.getTargetIdentifier());
        retryJob.setAttempts(retryJobDO.getAttempts());
        retryJob.setMaxAttempts(retryJobDO.getMaxAttempts());
        retryJob.setLastRetryTime(retryJobDO.getLastRetryTime());
        retryJob.setNextRetryTime(retryJobDO.getNextRetryTime());
        retryJob.setRetryRule(retryJobDO.getRetryRule());
        retryJob.setExtInfo(JSONUtil.parseMap(retryJobDO.getExtInfo()));
        retryJob.setRemark(retryJobDO.getRemark());
        retryJob.setStatus(RetryJobStatusEnum.getType(retryJobDO.getStatus()));
        retryJob.setCreator(retryJobDO.getCreator());
        retryJob.setCreatedTime(retryJobDO.getCreatedTime());
        retryJob.setModifier(retryJobDO.getModifier());
        retryJob.setModifiedTime(retryJobDO.getModifiedTime());

        return retryJob;
    }

    /**
     * toRetryJobDO
     *
     * @param retryJob
     * @return
     */
    public static RetryJobDO toRetryJobDO(RetryJob retryJob) {
        if (retryJob == null) {
            return null;
        }

        RetryJobDO retryJobDO = new RetryJobDO();
        retryJobDO.setBizType(retryJob.getBizType());
        retryJobDO.setBizId(retryJob.getBizId());
        retryJobDO.setPriority(Integer.parseInt(retryJob.getPriority().getCode()));
        retryJobDO.setTargetIdentifier(retryJob.getTargetIdentifier());
        retryJobDO.setAttempts(retryJob.getAttempts());
        retryJobDO.setMaxAttempts(retryJob.getMaxAttempts());
        retryJobDO.setLastRetryTime(retryJob.getLastRetryTime());
        retryJobDO.setNextRetryTime(retryJob.getNextRetryTime());
        retryJobDO.setRetryRule(retryJob.getRetryRule());
        retryJobDO.setExtInfo(JSONUtil.toJSONString(retryJob.getExtInfo()));
        retryJobDO.setRemark(retryJob.getRemark());
        retryJobDO.setStatus(retryJob.getStatus().getCode());
        retryJobDO.setCreator(retryJob.getCreator());
        retryJobDO.setCreatedTime(retryJob.getCreatedTime());
        retryJobDO.setModifier(retryJob.getModifier());
        retryJobDO.setModifiedTime(retryJob.getModifiedTime());

        return retryJobDO;
    }

    /**
     * toRetryParam
     *
     * @param retryParamDO
     * @return
     */
    public static RetryParam toRetryParam(RetryParamDO retryParamDO) {
        if (retryParamDO == null) {
            return null;
        }

        RetryParam retryParam = new RetryParam();
        retryParam.setId(String.valueOf(retryParamDO.getId()));
        retryParam.setRetryJobId(retryParamDO.getRetryJobId());
        retryParam.setValue(retryParamDO.getValue());
        retryParam.setExtInfo(JSONUtil.parseMap(retryParamDO.getExtInfo()));
        retryParam.setRemark(retryParamDO.getRemark());
        retryParam.setStatus(RetryParamStatusEnum.getType(retryParamDO.getStatus()));
        retryParam.setCreator(retryParamDO.getCreator());
        retryParam.setCreatedTime(retryParamDO.getCreatedTime());
        retryParam.setModifier(retryParamDO.getModifier());
        retryParam.setModifiedTime(retryParamDO.getModifiedTime());
        return retryParam;
    }

    /**
     * toRetryParams
     *
     * @param retryParamDOList
     * @return
     */
    public static List<RetryParam> toRetryParams(List<RetryParamDO> retryParamDOList) {
        if (CollectionUtil.isEmpty(retryParamDOList)) {
            return null;
        }
        List<RetryParam> retryParams = new ArrayList<>(retryParamDOList.size());
        for (RetryParamDO retryParamDO : retryParamDOList) {
            RetryParam retryParam = toRetryParam(retryParamDO);
            if (retryParam != null) {
                retryParams.add(retryParam);
            }
        }
        return retryParams;
    }

    /**
     * toRetryParamDO
     *
     * @param retryParam
     * @return
     */
    public static RetryParamDO toRetryParamDO(RetryParam retryParam) {
        if (retryParam == null) {
            return null;
        }

        RetryParamDO retryParamDO = new RetryParamDO();
        retryParamDO.setRetryJobId(retryParam.getRetryJobId());
        retryParamDO.setValue(retryParam.getValue());
        retryParamDO.setExtInfo(JSONUtil.toJSONString(retryParam.getExtInfo()));
        retryParamDO.setRemark(retryParam.getRemark());
        retryParamDO.setStatus(retryParam.getStatus().getCode());
        retryParamDO.setCreator(retryParam.getCreator());
        retryParamDO.setCreatedTime(retryParam.getCreatedTime());
        retryParamDO.setModifier(retryParam.getModifier());
        retryParamDO.setModifiedTime(retryParam.getModifiedTime());
        return retryParamDO;
    }
}