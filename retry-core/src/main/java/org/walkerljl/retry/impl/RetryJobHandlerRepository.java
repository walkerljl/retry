package org.walkerljl.retry.impl;

import org.walkerljl.retry.RetryHandler;
import org.walkerljl.retry.impl.util.AssertUtil;
import org.walkerljl.retry.impl.util.StringUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * RetryJobHandlerRepository
 *
 * @author xingxun
 */
public class RetryJobHandlerRepository {

    /** Repository*/
    private static final Map<String, RetryHandler> REPOSITROY = new HashMap<String, RetryHandler>(0);

    /**
     * Register handler of retry job
     *
     * @param identifierId Identifier id of retry job
     * @param retryJobHandler Handler of retry job
     */
    public static void register(String identifierId, RetryHandler retryJobHandler) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(identifierId), "identifierId");
        AssertUtil.assertParam(retryJobHandler != null, "retryJobHandler");
        REPOSITROY.put(identifierId, retryJobHandler);
    }

    /**
     * Remove handler of retry job
     *
     * @param identifierId Identifier id of retry job
     */
    public static void unregister(String identifierId) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(identifierId), "identifierId");
        REPOSITROY.remove(identifierId);
    }

    /**
     * Look up handler of retry job
     *
     * @param identifierId Identifier id of retry job
     * @return
     */
    public static RetryHandler lookup(String identifierId) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(identifierId), "identifierId");
        return REPOSITROY.get(identifierId);
    }

    /**
     * Look up all handlers of retry job
     *
     * @return
     */
    public static Collection<RetryHandler> lookupAll() {
        return Collections.unmodifiableCollection(REPOSITROY.values());
    }
}