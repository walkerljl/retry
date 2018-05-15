package org.walkerljl.retry.alarm.impl;

import org.walkerljl.retry.alarm.Alarm;
import org.walkerljl.retry.alarm.AlarmInfo;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.logger.Logger;

/**
 * 日志报警器
 *
 * @author xingxun
 */
public class LoggerAlarm implements Alarm {

    /** Logger*/
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY_ALARM);

    @Override
    public void alarm(AlarmInfo alarmInfo) {
        if (alarmInfo == null) {
            return;
        }
        if (LOGGER.isInfoEnabled()) {
            LoggerUtil.info(LOGGER, String.format("[RETRY ALARM]key:%s,description:%s.",
                    alarmInfo.getKey(), alarmInfo.getDescription()));
        }
    }
}