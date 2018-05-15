package org.walkerljl.retry.impl.util;

import java.util.Date;

import org.walkerljl.retry.model.RetryConfig;
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
        if (retryJob == null) {
            return null;
        }
        RetryLog retryLog = new RetryLog();
        retryLog.setRetryJobId(retryJob.getId());
        retryLog.setAttempts(retryJob.getAttempts());
        retryLog.setCreator(retryJob.getCreator());
        retryLog.setCreatedTime(new Date());
        return retryLog;
    }

    public static RetryLog buildFailureRetryLog(RetryJob retryJob, String errorMsg) {
        if (retryJob == null) {
            return null;
        }
        RetryLog retryLog = buildBaseRetryLog(retryJob);
        retryLog.setStatus(RetryLogStatusEnum.FAILURE);
        retryLog.setDescription(errorMsg);
        return retryLog;
    }

    public static RetryLog buildSuccessRetryLog(RetryJob retryJob) {
        if (retryJob == null) {
            return null;
        }
        RetryLog retryLog = buildBaseRetryLog(retryJob);
        if (retryLog == null) {
            return null;
        }
        retryLog.setStatus(RetryLogStatusEnum.SUCCESS);
        return retryLog;
    }

    public static LockRetryJobParam buildLockRetryJobParam(RetryConfig retryConfig, RetryJob retryJob) {
        if (retryJob == null) {
            return null;
        }
        if (retryConfig == null) {
            return null;
        }
        LockRetryJobParam lockRetryJobParam = new LockRetryJobParam();
        Date currentTime = new Date();
        lockRetryJobParam.setRetryJobId(retryJob.getId());
        lockRetryJobParam.setRetryTimeout(retryConfig.getJobRetryTimeout());
        lockRetryJobParam.setLastRetryTime(currentTime);
        return lockRetryJobParam;
    }

    public static UnlockRetryJobParam buildUnlockRetryJobParam(RetryJob retryJob, boolean isSuccess) {
        if (retryJob == null) {
            return null;
        }

        UnlockRetryJobParam unlockRetryJobParam = new UnlockRetryJobParam();
        unlockRetryJobParam.setRetryJobId(retryJob.getId());
        unlockRetryJobParam.setStatus((isSuccess ? RetryJobStatusEnum.PROCESSED
                : RetryJobStatusEnum.FAILURE));
        Date nextRetryTime = RetryIntervalCalculator.calculateNextRetryTime(retryJob.getLastRetryTime(),
                retryJob.getRetryRule(), retryJob.getAttempts() + 1);
        unlockRetryJobParam.setNextRetryTime(nextRetryTime);
        return unlockRetryJobParam;
    }
}