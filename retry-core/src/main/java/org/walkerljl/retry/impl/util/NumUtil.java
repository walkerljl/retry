package org.walkerljl.retry.impl.util;

/**
 * NumUtil
 *
 * @author xingxun
 */
public class NumUtil {

    /**
     * intValue
     *
     * @param number
     * @return
     */
    public static int intValue(Number number) {
        return number == null ? 0 : number.intValue();
    }

    /**
     * longValue
     *
     * @param number
     * @return
     */
    public static long longValue(Number number) {
        return number == null ? 0L : number.longValue();
    }

}