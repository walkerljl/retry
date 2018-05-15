package org.walkerljl.retry.standard;

import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.standard.abstracts.AbstractMachine;

/**
 * Bootstrap
 *
 * @author xingxun
 * @Date 2016/12/6
 */
public class Bootstrap extends AbstractMachine implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * Entrance
     *
     * @param args
     * @throws CannotStartMachineException
     */
    public static void main(String[] args) throws CannotStartMachineException {
        new Bootstrap().start();
        synchronized (Bootstrap.class) {
            while (true) {
                try {
                    Bootstrap.class.wait();
                } catch (InterruptedException ex) {}
            }
        }
    }

    @Override
    public void processStart() throws CannotStartMachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, "process start.");
        }
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        if (LOGGER.isDebugEnabled()) {
            LoggerUtil.debug(LOGGER, "process stop.");
        }
    }

    @Override
    public String getId() {
        return "bootstrap";
    }

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getGroup() {
        return "retry";
    }
}
