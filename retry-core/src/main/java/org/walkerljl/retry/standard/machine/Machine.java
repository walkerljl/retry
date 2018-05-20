package org.walkerljl.retry.standard.machine;

import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.retry.standard.machine.exception.MachineException;
import org.walkerljl.retry.standard.resource.Resource;

/**
 * 机器
 *
 * @author xingxun
 */
public interface Machine extends Resource {

    /**
     * 启动
     *
     * @throws CannotStartMachineException
     */
    void start() throws CannotStartMachineException;

    /**
     * 启动成功时的回调函数
     *
     * @throws MachineException
     */
    void callbackOnSuccessToStart() throws MachineException;

    /**
     * 启动失败时的回调函数
     *
     * @throws MachineException
     */
    void callbackOnFailureToStart() throws MachineException;

    /**
     * 运行
     *
     * @throws MachineException
     */
    void run() throws MachineException;

    /**
     * 停止
     *
     * @throws CannotStopMachineException
     */
    void stop() throws CannotStopMachineException;

    /**
     * 停止成功时回调
     *
     * @throws MachineException
     */
    void callbackOnSuccessToStop() throws MachineException;

    /**
     * 停止失败时回调
     *
     * @throws MachineException
     */
    void callbackOnFailureToStop() throws MachineException;

    /**
     * 重启，先停止，然后启动
     *
     * @throws CannotStartMachineException
     * @throws CannotStopMachineException
     */
    void restart() throws CannotStartMachineException, CannotStopMachineException;

    /**
     * 判断是否正在运行中
     *
     * @return true：运行中，false：未运行
     */
    boolean isRunning();
}
