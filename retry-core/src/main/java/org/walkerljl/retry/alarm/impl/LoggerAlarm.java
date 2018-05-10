package org.walkerljl.retry.alarm.impl;

import org.walkerljl.retry.alarm.Alarm;
import org.walkerljl.retry.alarm.AlarmInfo;
import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerFactory;
import org.walkerljl.retry.log.logger.LoggerNames;
import org.walkerljl.retry.log.util.LoggerUtil;

/**
 * Alarm of log
 *
 * @author xingxun
 */
public class LoggerAlarm implements Alarm {

    /** Logger*/
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.ALARM);

    @Override
    public void alarm(AlarmInfo alarmInfo) {

        if (alarmInfo == null) {
            return;
        }

        LoggerUtil.error(LOGGER, null, String.format("[RETRY ALARM]key:%s,description:%s.",
                alarmInfo.getKey(), alarmInfo.getDescription()));
    }
}