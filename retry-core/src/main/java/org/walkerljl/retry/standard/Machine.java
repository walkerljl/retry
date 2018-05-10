package org.walkerljl.retry.standard;

import org.walkerljl.retry.exception.machine.CannotStartMachineException;
import org.walkerljl.retry.exception.machine.CannotStopMachineException;
import org.walkerljl.retry.exception.machine.MachineException;

/**
 * Machine
 *
 * @author xingxun
 */
public interface Machine extends Resource {

    /**
     * Start
     *
     * @throws CannotStartMachineException
     */
    void start() throws CannotStartMachineException;

    /**
     * Callback when success to start.
     *
     * @throws MachineException
     */
    void callbackOnSuccessToStart() throws MachineException;

    /**
     * Run
     *
     * @throws MachineException
     */
    void run() throws MachineException;

    /**
     * Callback when failure to start.
     *
     * @throws MachineException
     */
    void callbackOnFailureToStart() throws MachineException;

    /**
     * Stop
     *
     * @throws CannotStopMachineException
     */
    void stop() throws CannotStopMachineException;

    /**
     * Callback when success to stop.
     *
     * @throws MachineException
     */
    void callbackOnSuccessToStop() throws MachineException;

    /**
     * Callback when failure to stop.
     *
     * @throws MachineException
     */
    void callbackOnFailureToStop() throws MachineException;

    /**
     * Stop and start.
     *
     * @throws CannotStartMachineException
     * @throws CannotStopMachineException
     */
    void restart() throws CannotStartMachineException, CannotStopMachineException;

    /**
     * Check is running.
     *
     * @return true:is runing.
     */
    boolean isRunning();
}
