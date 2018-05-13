package org.walkerljl.retry.alarm;

/**
 * 警告器
 *
 * @author xingxun
 */
public interface Alarm {

    /**
     * 报警
     *
     * @param alarmInfo 报警信息
     */
    void alarm(AlarmInfo alarmInfo);
}