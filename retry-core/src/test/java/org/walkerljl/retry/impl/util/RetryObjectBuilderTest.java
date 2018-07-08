package org.walkerljl.retry.impl.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.DateUtil;
import org.walkerljl.retry.model.RetryConfig;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryLogStatusEnum;
import org.walkerljl.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.UnlockRetryJobParam;

/**
 * RetryObjectBuilderTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class RetryObjectBuilderTest  extends BaseUnitTest {

    @Test
    public void buildBaseRetryLog() {

        RetryLog actual = RetryObjectBuilder.buildBaseRetryLog(null);
        Assert.assertNull(actual);

        RetryJob retryJob = newRetryJob(1);
        actual = RetryObjectBuilder.buildBaseRetryLog(retryJob);
        Assert.assertEquals(actual.getRetryJobId(), retryJob.getId());
        Assert.assertEquals(actual.getAttempts(), retryJob.getAttempts());
        Assert.assertEquals(actual.getCreator(), retryJob.getCreator());
        Assert.assertNotNull(actual.getCreatedTime());
    }

    @Test
    public void buildFailureRetryLog() {

        String errorMsg = "errorMsg";
        RetryLog actual = RetryObjectBuilder.buildFailureRetryLog(null, errorMsg);
        Assert.assertNull(actual);

        RetryJob retryJob = newRetryJob(1);
        actual = RetryObjectBuilder.buildFailureRetryLog(retryJob, errorMsg);
        Assert.assertEquals(actual.getRetryJobId(), retryJob.getId());
        Assert.assertEquals(actual.getAttempts(), retryJob.getAttempts());
        Assert.assertEquals(actual.getCreator(), retryJob.getCreator());
        Assert.assertNotNull(actual.getCreatedTime());
        Assert.assertEquals(actual.getStatus(), RetryLogStatusEnum.FAILURE);
        Assert.assertEquals(actual.getDescription(), errorMsg);
    }

    @Test
    public void buildSuccessRetryLog() {

        RetryLog actual = RetryObjectBuilder.buildSuccessRetryLog(null);
        Assert.assertNull(actual);

        RetryJob retryJob = newRetryJob(1);
        actual = RetryObjectBuilder.buildSuccessRetryLog(retryJob);
        Assert.assertEquals(actual.getRetryJobId(), retryJob.getId());
        Assert.assertEquals(actual.getAttempts(), retryJob.getAttempts());
        Assert.assertEquals(actual.getCreator(), retryJob.getCreator());
        Assert.assertNotNull(actual.getCreatedTime());
        Assert.assertEquals(actual.getStatus(), RetryLogStatusEnum.SUCCESS);
        Assert.assertNull(actual.getDescription());
    }

    @Test
    public void buildLockRetryJobParam() {

        RetryConfig retryConfig = new RetryConfig();
        retryConfig.setJobRetryTimeout(new Random().nextLong());
        RetryJob retryJob = newRetryJob(1);

        LockRetryJobParam actual = RetryObjectBuilder.buildLockRetryJobParam(null, retryJob);
        Assert.assertNull(actual);
        actual = RetryObjectBuilder.buildLockRetryJobParam(retryConfig, null);
        Assert.assertNull(actual);

        actual = RetryObjectBuilder.buildLockRetryJobParam(retryConfig, retryJob);
        Assert.assertEquals(actual.getRetryJobId(), retryJob.getId());
        Assert.assertEquals(DateUtil.dateFormat(actual.getLastRetryTime(), "yyyy-MM-dd HH:mm:ss"),
                DateUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals(actual.getRetryTimeout(), Long.valueOf(retryConfig.getJobRetryTimeout()));
    }

    @Test
    public void buildUnlockRetryJobParam() {

        RetryJob retryJob = newRetryJob(1);

        UnlockRetryJobParam actual = RetryObjectBuilder.buildUnlockRetryJobParam(null, true);
        Assert.assertNull(actual);

        actual = RetryObjectBuilder.buildUnlockRetryJobParam(retryJob, true);
        Assert.assertEquals(actual.getRetryJobId(), retryJob.getId());
        Assert.assertEquals(actual.getStatus(), RetryJobStatusEnum.PROCESSED);
        Date expectedNextRetryTime = RetryIntervalCalculator.calculateNextRetryTime(
                retryJob.getRetryRule(), retryJob.getAttempts() + 1);
        Assert.assertEquals(DateUtil.dateFormat(actual.getNextRetryTime(), DateUtil.DATE_FOPRMAT_SECONDS),
                DateUtil.dateFormat(expectedNextRetryTime, DateUtil.DATE_FOPRMAT_SECONDS));

        actual = RetryObjectBuilder.buildUnlockRetryJobParam(retryJob, false);
        Assert.assertEquals(actual.getRetryJobId(), retryJob.getId());
        Assert.assertEquals(actual.getStatus(), RetryJobStatusEnum.FAILURE);
        expectedNextRetryTime = RetryIntervalCalculator.calculateNextRetryTime(
                retryJob.getRetryRule(), retryJob.getAttempts() + 1);
        Assert.assertEquals(DateUtil.dateFormat(actual.getNextRetryTime(), DateUtil.DATE_FOPRMAT_SECONDS),
                DateUtil.dateFormat(expectedNextRetryTime, DateUtil.DATE_FOPRMAT_SECONDS));
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

    public static RetryJob newRetryJob(int index) {
        RetryJob retryJob = new RetryJob();

        retryJob.setId(String.valueOf(index));
        retryJob.setBizType("bizType" + index);
        retryJob.setBizId("bizId" + index);
        retryJob.setPriority(RetryPriorityEnum.NORMAL);
        retryJob.setAttempts(index);
        retryJob.setMaxAttempts(retryJob.getAttempts() + 1);
        retryJob.setTargetIdentifier("targetIdentifier" + index);
        retryJob.setRetryRule("2" + index);
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


}
