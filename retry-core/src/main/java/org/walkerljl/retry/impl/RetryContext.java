package org.walkerljl.retry.impl;

import org.walkerljl.retry.impl.defaults.DefaultAttributeAccessor;

/**
 * 重试上下文
 *
 * @author xingxun
 */
public class RetryContext extends DefaultAttributeAccessor implements AttributeAccessor {

    public static final String NAME = "context.name";

    public static final String BUSINESS_PARAMS = "context.params";

    public static final String EXECUTOR_ID = "context.executorId";

    public static final String EXECUTOR_CONFIG = "context.executorConfig";

    public static final String RETRY_CONFIG = "context.retryConfig";
}