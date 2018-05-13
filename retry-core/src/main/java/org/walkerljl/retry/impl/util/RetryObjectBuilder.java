package org.walkerljl.retry.impl.util;

import java.util.Date;

import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.model.RetryLog;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.model.enums.RetryLogStatusEnum;
import org.walkerljl.retry.model.param.LockRetryJobParam;
import org.walkerljl.retry.model.param.UnlockRetryJobParam;

/**
 * RetryObjectBuilder
 *
 * @author xingxun
 */
public class RetryObjectBuilder {

    public static RetryLog buildBaseRetryLog(RetryJob retryJob) {
        RetryLog retryLog = new RetryLog();
        retryLog.setRetryJobId(retryJob.getId());
        retryLog.setAttempts(retryJob.getAttempts());
        retryLog.setCreator("");
        retryLog.setCreatedTime(new Date());
        return retryLog;
    }

    public static RetryLog buildFailureRetryLog(RetryJob retryJob, String errorMsg) {
        RetryLog retryLog = buildBaseRetryLog(retryJob);
        retryLog.setStatus(RetryLogStatusEnum.FAILURE);
        retryLog.setDescription(errorMsg == null ? "" : errorMsg);
        return retryLog;
    }

    public static RetryLog buildSuccessRetryLog(RetryJob retryJob) {
        RetryLog retryLog = buildBaseRetryLog(retryJob);
        retryLog.setStatus(RetryLogStatusEnum.SUCCESS);
        return retryLog;
    }

    public static LockRetryJobParam buildLockRetryJobParam(RetryJob retryJob) {
        LockRetryJobParam lockRetryJobParam = new LockRetryJobParam();
        Date currentTime = new Date();
        lockRetryJobParam.setRetryJobId(retryJob.getId());
        lockRetryJobParam.setLastRetryTime(currentTime);
        lockRetryJobParam.setModifiedTime(currentTime);
        return lockRetryJobParam;
    }

    public static UnlockRetryJobParam buildUnlockRetryJobParam(RetryJob retryJob, boolean isSuccess) {
        UnlockRetryJobParam unlockRetryJobParam = new UnlockRetryJobParam();

        unlockRetryJobParam.setRetryJobId(retryJob.getId());
        unlockRetryJobParam.setStatus((isSuccess ? RetryJobStatusEnum.PROCESSED
                : RetryJobStatusEnum.FAILURE));
        Date nextRetryTime = RetryIntervalCalculator.getNextRetryTime(retryJob.getLastRetryTime(),
                retryJob.getRetryRule(), retryJob.getAttempts() + 1);
        unlockRetryJobParam.setNextRetryTime(nextRetryTime);
        unlockRetryJobParam.setModifiedTime(new Date());
        return unlockRetryJobParam;
    }
}