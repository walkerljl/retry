package org.walkerljl.retry.impl.util;

/**
 * StringUtil
 *
 * @author xingxun
 */
public class StringUtil {

    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    public static boolean isNotEmpty(String string) {
        return string != null && !"".equals(string);
    }
}