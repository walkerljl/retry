package org.walkerljl.retry.impl.util;

import java.util.Calendar;
import java.util.Date;

/**
 * RetryIntervalCalculator
 *
 * @author xingxun
 */
public class RetryIntervalCalculator {

    private static final String INTERVAL_EXPRESSION_SEPERATOR     = ",";
    private static final int    INTERVAL_EXPRESSION_ITEM_LENGTH_1 = 1;
    private static final int    INTERVAL_EXPRESSION_ITEM_LENGTH_2 = 2;

    /**
     * 计算下次重试时间
     *
     * @param intervalExpression 间隔时间表达式
     * @param attempts 重试次数
     * @return
     */
    public static Date calculateNextRetryTime(String intervalExpression, int attempts) {
        if (StringUtil.isEmpty(intervalExpression)) {
            return new Date();
        }
        int interval = 0;
        String[] expressionItems = intervalExpression.split(INTERVAL_EXPRESSION_SEPERATOR);
        if (ArrayUtil.isEmpty(expressionItems)) {
            return new Date();
        } else if (expressionItems.length == INTERVAL_EXPRESSION_ITEM_LENGTH_1) {
            interval = Integer.valueOf(expressionItems[0]);
        } else if (expressionItems.length == INTERVAL_EXPRESSION_ITEM_LENGTH_2) {
            int delay = Integer.valueOf(expressionItems[0]);
            int multipliper = Integer.valueOf(expressionItems[1]);
            interval = delay * attempts * multipliper;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, interval);
        return calendar.getTime();
    }

}