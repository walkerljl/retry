package org.walkerljl.retry.impl.util;

/**
 * StringUtil
 *
 * @author xingxun
 */
public class StringUtil {

    /**
     * isEmpty
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    /**
     * isNotEmpty
     *
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        return string != null && !"".equals(string.trim());
    }
}