package org.walkerljl.retry.alarm;

/**
 * Alarm
 *
 * @author xingxun
 */
public interface Alarm {

    /**
     * Do alarm
     *
     * @param alarmInfo Alarm information
     */
    void alarm(AlarmInfo alarmInfo);
}