package org.walkerljl.retry.impl.util;

/**
 * ArrayUtil
 *
 * @author xingxun
 */
public class ArrayUtil {

    /**
     * isEmpty
     *
     * @param array
     * @param <E>
     * @return
     */
    public static <E> boolean isEmpty(E[] array) {
        return array == null || array.length == 0;
    }

    /**
     * concat
     *
     * @param separator
     * @param objects
     * @return
     */
    public static String concat(String separator, Object... objects) {
        if (isEmpty(objects)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(objects[0]);
        for (int index = 1; index < objects.length; index++) {
            sb.append(separator).append(String.valueOf(objects[index]));
        }
        return sb.toString();
    }
}