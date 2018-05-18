package org.walkerljl.retry.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.db.dao.dataobject.RetryJobDO;
import org.walkerljl.retry.db.dao.dataobject.RetryLogDO;
import org.walkerljl.retry.db.dao.dataobject.RetryParamDO;
import org.walkerljl.retry.db.util.JSONUtil;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryLogStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;

/**
 *
 * @author xingxun
 */
public class ModelAndDOConverterTest extends BaseUnitTest {

    @Test
    public void toRetryLogDO() {

        RetryLogDO actual = ModelAndDOConverter.toRetryLogDO(null);
        Assert.assertNull(actual);

        RetryLog retryLog = newRetryLog(1);
        actual = ModelAndDOConverter.toRetryLogDO(retryLog);

        Assert.assertEquals(String.valueOf(actual.getId()), retryLog.getId());
        Assert.assertEquals(actual.getRetryJobId(), retryLog.getRetryJobId());
        Assert.assertEquals(actual.getAttempts(), retryLog.getAttempts());
        Assert.assertEquals(actual.getDescription(), retryLog.getDescription());
        Assert.assertEquals(actual.getStatus(), retryLog.getStatus().getCode());
        Assert.assertEquals(actual.getRemark(), retryLog.getRemark());
        Assert.assertEquals(actual.getCreatedTime(), retryLog.getCreatedTime());
        Assert.assertEquals(actual.getCreator(), retryLog.getCreator());
    }

    @Test
    public void toRetryJobs() {

        List<RetryJob> actual = ModelAndDOConverter.toRetryJobs(null);
        Assert.assertNull(actual);

        List<RetryJobDO> retryJobDOList = new ArrayList<>(3);
        retryJobDOList.add(newRetryJobDO(1));
        retryJobDOList.add(null);
        retryJobDOList.add(newRetryJobDO(2));

        actual = ModelAndDOConverter.toRetryJobs(retryJobDOList);
        Assert.assertEquals(actual.size(), 2);

        assertRetryJob(actual.get(0), retryJobDOList.get(0));
        assertRetryJob(actual.get(1), retryJobDOList.get(2));
    }

    @Test
    public void toRetryJob() {
        RetryJob actual = ModelAndDOConverter.toRetryJob(null);
        Assert.assertNull(actual);

        RetryJobDO retryJobDO = newRetryJobDO(1);
        actual = ModelAndDOConverter.toRetryJob(retryJobDO);
        assertRetryJob(actual, retryJobDO);
    }

    @Test
    public void toRetryJobDO() {
        RetryJobDO actual = ModelAndDOConverter.toRetryJobDO(null);
        Assert.assertNull(actual);

        RetryJob retryJob = newRetryJob(1);
        actual = ModelAndDOConverter.toRetryJobDO(retryJob);
        assertRetryJob(retryJob, actual);
    }

    @Test
    public void toRetryParam() {
        RetryParam actual = ModelAndDOConverter.toRetryParam(null);
        Assert.assertNull(actual);

        RetryParamDO retryParamDO = newRetryParamDO(1);
        actual = ModelAndDOConverter.toRetryParam(retryParamDO);
        assertRetryParam(actual, retryParamDO);
    }

    @Test
    public void toRetryParamDO() {
        RetryParamDO actual = ModelAndDOConverter.toRetryParamDO(null);
        Assert.assertNull(actual);

        RetryParam retryParam = newRetryParam(1);
        actual = ModelAndDOConverter.toRetryParamDO(retryParam);
        assertRetryParam(retryParam, actual);
    }

    @Test
    public void toRetryParams() {

        List<RetryParam> actual = ModelAndDOConverter.toRetryParams(null);
        Assert.assertNull(actual);

        List<RetryParamDO> retryParamDOList = new ArrayList<>(3);
        retryParamDOList.add(newRetryParamDO(1));
        retryParamDOList.add(null);
        retryParamDOList.add(newRetryParamDO(2));

        actual = ModelAndDOConverter.toRetryParams(retryParamDOList);
        Assert.assertEquals(actual.size(), 2);

        assertRetryParam(actual.get(0), retryParamDOList.get(0));
        assertRetryParam(actual.get(1), retryParamDOList.get(2));
    }

    private void assertRetryParam(RetryParam actual, RetryParamDO expected) {
        Assert.assertEquals(actual.getId(), String.valueOf(expected.getId()));
        Assert.assertEquals(actual.getRetryJobId(), expected.getRetryJobId());
        Assert.assertEquals(actual.getValue(), expected.getValue());
        Assert.assertEquals(actual.getStatus().getCode(), expected.getStatus());
        Assert.assertEquals(actual.getExtInfo(), JSONUtil.parseMap(expected.getExtInfo()));
        Assert.assertEquals(actual.getRemark(), expected.getRemark());
        Assert.assertEquals(actual.getCreatedTime(), expected.getCreatedTime());
        Assert.assertEquals(actual.getCreator(), expected.getCreator());
        Assert.assertEquals(actual.getModifiedTime(), expected.getModifiedTime());
        Assert.assertEquals(actual.getModifier(), expected.getModifier());
    }

    public static RetryParam newRetryParam(int index) {
        RetryParam retryParam = new RetryParam();
        retryParam.setId(String.valueOf(index));
        retryParam.setRetryJobId(String.valueOf(index));
        retryParam.setValue("value" + index);
        Map<String, String> extInfo = new HashMap<>(1);
        extInfo.put("key" + index, "value" + index);
        retryParam.setExtInfo(extInfo);
        retryParam.setStatus(RetryParamStatusEnum.NORMAL);
        retryParam.setRemark("remark");
        retryParam.setCreatedTime(new Date());
        retryParam.setCreator("creator");
        retryParam.setModifiedTime(retryParam.getCreatedTime());
        retryParam.setModifier(retryParam.getCreator());

        return retryParam;
    }

    public static RetryParamDO newRetryParamDO(int index) {
        RetryParamDO retryParamDO = new RetryParamDO();
        retryParamDO.setId(Long.valueOf(index));
        retryParamDO.setRetryJobId(String.valueOf(index));
        retryParamDO.setValue("value" + index);
        Map<String, Object> extInfo = new HashMap<>(1);
        extInfo.put("key" + index, "value" + index);
        retryParamDO.setExtInfo(JSONUtil.toJSONString(extInfo));
        retryParamDO.setStatus(RetryParamStatusEnum.NORMAL.getCode());
        retryParamDO.setRemark("remark");
        retryParamDO.setCreatedTime(new Date());
        retryParamDO.setCreator("creator");
        retryParamDO.setModifiedTime(retryParamDO.getCreatedTime());
        retryParamDO.setModifier(retryParamDO.getCreator());

        return retryParamDO;
    }

    public static void assertRetryJob(RetryJob actual, RetryJobDO expected) {
        Assert.assertEquals(actual.getId(), String.valueOf(expected.getId()));
        Assert.assertEquals(actual.getBizType(), expected.getBizType());
        Assert.assertEquals(actual.getBizId(), expected.getBizId());
        Assert.assertEquals(actual.getPriority().getCode(), String.valueOf(expected.getPriority()));
        Assert.assertEquals(actual.getAttempts(), expected.getAttempts());
        Assert.assertEquals(actual.getMaxAttempts(), expected.getMaxAttempts());
        Assert.assertEquals(actual.getTargetIdentifier(), expected.getTargetIdentifier());
        Assert.assertEquals(actual.getRetryRule(), expected.getRetryRule());
        Assert.assertEquals(actual.getLastRetryTime(), expected.getLastRetryTime());
        Assert.assertEquals(actual.getNextRetryTime(), expected.getNextRetryTime());
        Assert.assertEquals(actual.getStatus().getCode(), expected.getStatus());
        Assert.assertEquals(actual.getExtInfo(), JSONUtil.parseMap(expected.getExtInfo()));
        Assert.assertEquals(actual.getRemark(), expected.getRemark());
        Assert.assertEquals(actual.getCreatedTime(), expected.getCreatedTime());
        Assert.assertEquals(actual.getCreator(), expected.getCreator());
        Assert.assertEquals(actual.getModifiedTime(), expected.getModifiedTime());
        Assert.assertEquals(actual.getModifier(), expected.getModifier());
    }

    public static RetryLog newRetryLog(int index) {
        RetryLog retryLog = new RetryLog();
        retryLog.setId(String.valueOf(index));
        retryLog.setRetryJobId("retryJobId" + index);
        retryLog.setAttempts(index);
        retryLog.setDescription("description" + index);
        retryLog.setStatus(RetryLogStatusEnum.SUCCESS);
        retryLog.setRemark("remark");
        retryLog.setCreatedTime(new Date());
        retryLog.setCreator("creator");

        return retryLog;
    }

    public static RetryJobDO newRetryJobDO(int index) {
        RetryJobDO retryJobDO = new RetryJobDO();

        retryJobDO.setId(new Long(String.valueOf(index)));
        retryJobDO.setBizType("bizType" + index);
        retryJobDO.setBizId("bizId" + index);
        retryJobDO.setPriority(Integer.valueOf(RetryPriorityEnum.NORMAL.getCode()));
        retryJobDO.setAttempts(index);
        retryJobDO.setMaxAttempts(retryJobDO.getAttempts() + 1);
        retryJobDO.setTargetIdentifier("targetIdentifier" + index);
        retryJobDO.setRetryRule("retryRule" + index);
        retryJobDO.setLastRetryTime(new Date());
        retryJobDO.setNextRetryTime(new Date());
        retryJobDO.setStatus(RetryJobStatusEnum.UNPROCESS.getCode());
        Map<String, String> extInfo = new HashMap<>(1);
        extInfo.put("key" + index, "value" + index);
        retryJobDO.setExtInfo(JSONUtil.toJSONString(extInfo));
        retryJobDO.setRemark("remark");
        retryJobDO.setCreatedTime(new Date());
        retryJobDO.setCreator("creator");
        retryJobDO.setModifiedTime(retryJobDO.getCreatedTime());
        retryJobDO.setModifier(retryJobDO.getCreator());

        return retryJobDO;
    }

    public static RetryJob newRetryJob(int index) {
        RetryJob retryJob = new RetryJob();

        retryJob.setId(String.valueOf(index));
        retryJob.setBizType("bizType" + index);
        retryJob.setBizId("bizId" + index);
        retryJob.setPriority(RetryPriorityEnum.NORMAL);
        retryJob.setAttempts(index);
        retryJob.setMaxAttempts(retryJob.getAttempts() + 1);
        retryJob.setTargetIdentifier("targetIdentifier" + index);
        retryJob.setRetryRule("retryRule" + index);
        retryJob.setLastRetryTime(new Date());
        retryJob.setNextRetryTime(new Date());
        retryJob.setStatus(RetryJobStatusEnum.UNPROCESS);
        Map<String, String> extInfo = new HashMap<>(1);
        extInfo.put("key" + index, "value" + index);
        retryJob.setExtInfo(extInfo);
        retryJob.setRemark("remark");
        retryJob.setCreatedTime(new Date());
        retryJob.setCreator("creator");
        retryJob.setModifiedTime(retryJob.getCreatedTime());
        retryJob.setModifier(retryJob.getCreator());

        return retryJob;
    }
}