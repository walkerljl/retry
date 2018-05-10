package org.walkerljl.retry.util;

import java.util.Collection;

/**
 * CollectionUtil
 *
 * @author xingxun
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}