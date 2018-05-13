package org.walkerljl.retry.impl.util;

import java.util.Calendar;
import java.util.Date;

import org.walkerljl.retry.exception.RetryException;

/**
 * RetryIntervalCalculator
 *
 * @author xingxun
 */
public class RetryIntervalCalculator {

    private static final String INTERVAL_EXPRESSION_SEPERATOR = ",";

    public static Date getNextRetryTime(Date currentTime, String intervalExpression, int times) {
        if (intervalExpression == null || "".equals(intervalExpression.trim())) {
            return currentTime;
        }
        if (times <= 1) {
            return currentTime;
        }
        int interval = 0;
        String[] expressionItems = intervalExpression.split(INTERVAL_EXPRESSION_SEPERATOR);
        if (expressionItems == null || expressionItems.length == 0) {
            return currentTime;
        } else if (expressionItems.length == 1) {
            interval = parseInt(expressionItems[0], intervalExpression) / 1000;
        } else if (expressionItems.length == 2) {
            int delay = parseInt(expressionItems[0], intervalExpression);
            int multipliper = parseInt(expressionItems[1], intervalExpression);
            interval = (delay / 1000) * (times - 1) * multipliper;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(currentTime);
        calendar.add(Calendar.SECOND, interval);
        return calendar.getTime();
    }

    private static int parseInt(String input, String intervalExpression) {
        int result = 0;
        try {
            result = Integer.parseInt(input);
        } catch (Exception e) {
            throw new RetryException(String.format("Invalid interval expression:%s.", intervalExpression), e);
        }
        return result;
    }
}