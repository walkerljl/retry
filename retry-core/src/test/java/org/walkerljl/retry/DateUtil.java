package org.walkerljl.retry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * DateUtil
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public class DateUtil {

    public static final String DATE_FOPRMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";

    public static String dateFormat(Date date, String format) {
        if (date == null || format == null || format.equals("")) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 修改时间
     * @param time
     * @param timeUnit
     * @param interval
     * @return
     */
    public static Date modifyTime(Date time, TimeUnit timeUnit, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(time);
        if (timeUnit == TimeUnit.DAYS) {
            calendar.add(Calendar.DAY_OF_MONTH, interval);
        } else if (timeUnit == TimeUnit.HOURS) {
            calendar.add(Calendar.HOUR, interval);
        } else if (timeUnit == TimeUnit.MINUTES) {
            calendar.add(Calendar.MINUTE, interval);
        } else if (timeUnit == TimeUnit.SECONDS) {
            calendar.add(Calendar.SECOND, interval);
        }
        return calendar.getTime();
    }
}
