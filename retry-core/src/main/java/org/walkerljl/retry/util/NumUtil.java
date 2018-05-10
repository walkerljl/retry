package org.walkerljl.retry.util;

/**
 * NumUtil
 *
 * @author xingxun
 */
public class NumUtil {

    public static int intValue(Number number) {
        return number == null ? 0 : number.intValue();
    }

}