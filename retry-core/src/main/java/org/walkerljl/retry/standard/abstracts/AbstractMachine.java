package org.walkerljl.retry.standard.abstracts;

import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.exception.machine.MachineException;
import org.walkerljl.retry.exception.resouce.CannotDestroyResourceException;
import org.walkerljl.retry.exception.resouce.CannotInitResourceException;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.standard.Machine;
import org.walkerljl.retry.standard.MachineRepository;

/**
 * AbstractMachine
 *
 * @author xingxun
 * @Date 2016/12/9
 */
public abstract class AbstractMachine implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMachine.class);

    private volatile boolean running = false;

    public abstract void processStart() throws CannotStartMachineException;

    public abstract void processStop() throws CannotStopMachineException;

    @Override
    public void start() throws CannotStartMachineException {

        long startTime = System.currentTimeMillis();
        try {
            if (!running) {
                synchronized (this) {
                    if (!running) {
                        if (LOGGER.isInfoEnabled()) {
                            LoggerUtil.info(LOGGER, String.format("%s is starting.", getServerName()));
                        }

                        init();
                        processStart();
                        MachineRepository.register(getGroup(), getId(), this);
                        running = true;

                        if (LOGGER.isInfoEnabled()) {
                            LoggerUtil.info(LOGGER,
                                    String.format("%s has started,consume %s milliseconds.", getServerName(),
                                            (System.currentTimeMillis() - startTime)));
                        }

                        //do call back
                        try {
                            callbackOnSuccessToStart();
                        } catch (Throwable ignore) {
                            //ignore
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LoggerUtil.error(LOGGER, e, (String.format("%s occurs some erros when starting.", getServerName())));
            //do call back
            try {
                callbackOnFailureToStart();
            } catch (Throwable ignore) {
                //ignore
            }
            throw new CannotStartMachineException(e);
        }
    }

    @Override
    public void callbackOnSuccessToStart() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, String.format("%s callback when success to start.", getServerName()));
        }
    }

    /**
     * Do process run.
     *
     * @throws MachineException
     */
    protected void processRun() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, String.format("%s has run.", getServerName()));
        }
    }

    @Override
    public void run() throws MachineException {
        if (isRunning()) {
            synchronized (this) {
                if (isRunning()) {
                    processRun();
                }
            }
        }
    }

    @Override
    public void callbackOnFailureToStart() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, String.format("%s callback when failure to start.", getServerName()));
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
                            LoggerUtil.info(LOGGER, String.format("%s is stopping.", getServerName()));
                        }

                        processStop();
                        MachineRepository.unregister(getGroup(), getId());
                        running = false;

                        if (LOGGER.isInfoEnabled()) {
                            LoggerUtil.info(LOGGER,
                                    String.format("%s has stopped,consume %s milliseconds.", getServerName(),
                                            (System.currentTimeMillis() - startTime)));
                        }

                        //do call back
                        try {
                            callbackOnSuccessToStop();
                        } catch (Throwable ignore) {
                            //ignore
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LoggerUtil.error(LOGGER, e, String.format("%s occurs some erros when stopping.", getServerName()));
            //do call back
            try {
                callbackOnFailureToStop();
            } catch (Throwable ignore) {
                //ignore
            }
            throw new CannotStopMachineException(e);
        }
    }

    @Override
    public void callbackOnSuccessToStop() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, String.format("%s callback when success to stop.", getServerName()));
        }
    }

    @Override
    public void callbackOnFailureToStop() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, String.format("%s callback when failure to stop.", getServerName()));
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
        synchronized (this) {
            return running;
        }
    }

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getInstanceId() {
        return this.toString();
    }

    @Override
    public void init() throws CannotInitResourceException {

    }

    @Override
    public void destroy() throws CannotDestroyResourceException {
        long startTime = System.currentTimeMillis();
        try {
            synchronized (this) {
                if (LOGGER.isInfoEnabled()) {
                    LoggerUtil.info(LOGGER, String.format("%s is destroying.", getServerName()));
                }

                stop();

                if (LOGGER.isInfoEnabled()) {
                    LoggerUtil.info(LOGGER, String.format("%s has destroied,consume %s milliseconds.", getServerName(),
                            (System.currentTimeMillis() - startTime)));
                }
            }
        } catch (Throwable e) {
            LoggerUtil.error(LOGGER, e, String.format("%s occurs some erros when destroying.", getServerName()));
            throw new CannotDestroyResourceException(e);
        }
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
