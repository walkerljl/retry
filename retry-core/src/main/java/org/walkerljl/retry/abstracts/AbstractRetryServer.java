package org.walkerljl.retry.abstracts;

import org.walkerljl.retry.RetryServer;
import org.walkerljl.retry.impl.RetryConstants;
import org.walkerljl.retry.impl.defaults.DefaultRetryJobLoader;
import org.walkerljl.retry.impl.executor.RetryJobExecutorConfig;
import org.walkerljl.retry.model.enums.RetryJobStatusEnum;
import org.walkerljl.retry.standard.machine.Machine;
import org.walkerljl.retry.standard.machine.MachineRepository;
import org.walkerljl.retry.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;

/**
 * AbstractRetryServer
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public abstract class AbstractRetryServer extends AbstractMachine implements RetryServer {

    @Override
    public String getId() {
        return RetryConstants.DEFAULT_COMPONENT_ID_RETRY_SERVER;
    }

    @Override
    public String getGroup() {
        return RetryConstants.COMPONENT_IDENTIFIER_RETRY_SERVER;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

        //重试任务加载器
        Machine retryJobLoader = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_LOADER,
                DefaultRetryJobLoader.class.getSimpleName());
        if (retryJobLoader != null) {
            retryJobLoader.start();
        }

        //重试任务分发器
        Machine retryJobDispatcher = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_DISPATCHER,
                DefaultRetryJobLoader.class.getSimpleName());
        if (retryJobDispatcher != null) {
            retryJobDispatcher.start();
        }

        //重试任务执行器
        Machine defaultRetryJobExecutor = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_EXECUTOR,
                RetryJobExecutorConfig.DEFAULT_EXECUTOR_ID);
        if (defaultRetryJobExecutor != null) {
            defaultRetryJobExecutor.start();
        }
        for (RetryJobStatusEnum retryJobStatus : RetryJobStatusEnum.values()) {
            Machine retryJobExecutor = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_EXECUTOR,
                    retryJobStatus.getCode());
            if (retryJobExecutor == null) {
                continue;
            }
            retryJobExecutor.start();
        }
    }

    @Override
    public void processStop() throws CannotStopMachineException {

        //重试任务加载器
        Machine retryJobLoader = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_LOADER,
                DefaultRetryJobLoader.class.getSimpleName());
        if (retryJobLoader != null) {
            retryJobLoader.stop();
        }

        //重试任务分发器
        Machine retryJobDispatcher = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_DISPATCHER,
                DefaultRetryJobLoader.class.getSimpleName());
        if (retryJobDispatcher != null) {
            retryJobDispatcher.stop();
        }

        //重试任务执行器
        Machine defaultRetryJobExecutor = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_EXECUTOR,
                RetryJobExecutorConfig.DEFAULT_EXECUTOR_ID);
        if (defaultRetryJobExecutor != null) {
            defaultRetryJobExecutor.stop();
        }
        for (RetryJobStatusEnum retryJobStatus : RetryJobStatusEnum.values()) {
            Machine retryJobExecutor = MachineRepository.lookup(RetryConstants.COMPONENT_IDENTIFIER_JOB_EXECUTOR,
                    retryJobStatus.getCode());
            if (retryJobExecutor == null) {
                continue;
            }
            retryJobExecutor.stop();
        }
    }
}
