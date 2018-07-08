package org.walkerljl.retry.standard.machine.abstracts;

import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.standard.machine.Machine;
import org.walkerljl.retry.standard.machine.MachineRepository;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.retry.standard.machine.exception.MachineException;
import org.walkerljl.retry.standard.resource.abstracts.AbstractResource;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;


/**
 * AbstractMachine
 *
 * @author xingxun
 * @Date 2016/12/9
 */
public abstract class AbstractMachine extends AbstractResource implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY);

    /** 是否初始化标志*/
    private volatile boolean running = false;

    /**
     * 处理启动
     *
     * @throws CannotStartMachineException
     */
    public abstract void processStart() throws CannotStartMachineException;

    /**
     * 处理停止
     *
     * @throws CannotStopMachineException
     */
    public abstract void processStop() throws CannotStopMachineException;

    @Override
    public void processInit() throws CannotInitResourceException {

    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {

    }

    @Override
    public void start() throws CannotStartMachineException {

        long startTime = System.currentTimeMillis();
        try {
            if (!running) {
                synchronized (this) {
                    if (!running) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s is starting.", getServerName()));
                        }

                        init();
                        processStart();
                        MachineRepository.register(getGroup(), getId(), this);
                        running = true;

                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has started,consume %s milliseconds.", getServerName(), (System.currentTimeMillis() - startTime)));
                        }

                        //do call back
                        try {
                            callbackOnSuccessToStart();
                        } catch (Throwable e) {
                            LOGGER.error("Fail to callback on success to start.", e);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when starting.", getServerName()), e);
            //do call back
            try {
                callbackOnFailureToStart();
            } catch (Throwable e2) {
                LOGGER.error("Fail to callback on failure to start.", e2);
            }
            throw new CannotStartMachineException(e);
        }
    }

    @Override
    public void callbackOnSuccessToStart() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when success to start.", getServerName()));
        }
    }

    /**
     * Do process run.
     *
     * @throws MachineException
     */
    protected void processRun() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s run once.", getServerName()));
        }
    }

    @Override
    public void run() throws MachineException {
        if (isRunning()) {
            processRun();
        }
    }

    @Override
    public void callbackOnFailureToStart() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when failure to start.", getServerName()));
        }
    }

    @Override
    public void stop() throws CannotStopMachineException {
        long startTime = System.currentTimeMillis();
        try {
            if (running) {
                synchronized (this) {
                    if (running) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s is stopping.", getServerName()));
                        }

                        processStop();
                        MachineRepository.unregister(getGroup(), getId());
                        running = false;

                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has stopped,consume %s milliseconds.", getServerName(), (System.currentTimeMillis() - startTime)));
                        }

                        //do call back
                        try {
                            callbackOnSuccessToStop();
                        } catch (Throwable e) {
                            LOGGER.error("Fail to callback on success to stop.", e);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when stopping.", getServerName()), e);
            //do call back
            try {
                callbackOnFailureToStop();
            } catch (Throwable e2) {
                LOGGER.error("Fail to callback on failure to stop.", e2);
            }
            throw new CannotStopMachineException(e);
        }
    }

    @Override
    public void callbackOnSuccessToStop() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when success to stop.", getServerName()));
        }
    }

    @Override
    public void callbackOnFailureToStop() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when failure to stop.", getServerName()));
        }
    }

    @Override
    public void restart() throws CannotStartMachineException, CannotStopMachineException {
        synchronized (this) {
            stop();
            start();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * Get service name
     *
     * @return
     */
    protected String getServerName() {
        return String.format("Machine[id=%s,group=%s,instanceId=%s]", getId(), getGroup(), getInstanceId());
    }
}
