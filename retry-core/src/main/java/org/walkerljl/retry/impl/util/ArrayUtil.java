package org.walkerljl.retry.impl.util;

/**
 *
 * @author xingxun
 */
public class ArrayUtil {

    public static <E> boolean isEmpty(E[] array) {
        return array == null || array.length == 0;
    }

    public static String concat(String separator, Object... objects) {
        if (isEmpty(objects)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(objects[0]);
        for (int index = 1; index < objects.length; index++) {
            sb.append(String.valueOf(objects[index])).append(separator);
        }
        return sb.toString();
    }
}