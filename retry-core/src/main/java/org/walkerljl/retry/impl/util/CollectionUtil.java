package org.walkerljl.retry.impl.util;

import java.util.Collection;

/**
 * CollectionUtil
 *
 * @author xingxun
 */
public class CollectionUtil {

    /**
     * 是否为空
     *
     * @param collection 集合
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}