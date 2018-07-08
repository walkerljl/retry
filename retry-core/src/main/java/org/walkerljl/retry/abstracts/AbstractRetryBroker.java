package org.walkerljl.retry.abstracts;

import org.walkerljl.retry.RetryBroker;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.log.invocation.InvocationInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerDetailUtil;
import org.walkerljl.retry.impl.log.util.LoggerDigestUtil;
import org.walkerljl.retry.impl.util.StringUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryJob;
import org.walkerljl.retry.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;

/**
 * AbstractRetryBroker
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public abstract class AbstractRetryBroker extends AbstractMachine implements RetryBroker {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_BROKER_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_BROKER_DETAIL);

    @Override
    public String getId() {
        return RetryConstants.DEFAULT_COMPONENT_ID_RETRY_BROKER;
    }

    @Override
    public String getGroup() {
        return RetryConstants.COMPONENT_IDENTIFIER_RETRY_BROKER;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public String submit(RetryJob retryJob) {

        InvocationInfo<Object[], String> invocationInfo = new InvocationInfo<>(getClass(),
                "submit", new Object[]{retryJob, isRunning()});
        String retryJobId = null;
        try {
            if (retryJob != null && isRunning()) {
                retryJobId = processSubmit(retryJob);
            }
            invocationInfo.markSuccess(retryJobId);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
        return retryJobId;
    }

    @Override
    public void markRetryJobToCompleted(String retryJobId) {

        InvocationInfo<Object[], String> invocationInfo = new InvocationInfo<>(getClass(),
                "markRetryJobToCompleted", new Object[]{retryJobId, isRunning()});
        try {
            if (StringUtil.isNotEmpty(retryJobId)
                    && isRunning()) {
                processMarkRetryJobToCompleted(retryJobId);
            }
            invocationInfo.markSuccess();
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    /**
     * 处理提交重试任务
     *
     * @param retryJob 重试任务
     * @return
     */
    public abstract String processSubmit(RetryJob retryJob);

    /**
     * 处理标注重试任务已经完成
     *
     * @param retryJobId 重试任务ID
     */
    public abstract void processMarkRetryJobToCompleted(String retryJobId);

}
