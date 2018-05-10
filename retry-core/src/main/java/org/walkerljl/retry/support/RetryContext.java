package org.walkerljl.retry.support;

import org.walkerljl.retry.support.impl.AttributeAccessorSupport;

/**
 * Retry context
 *
 * @author xingxun
 */
public class RetryContext extends AttributeAccessorSupport implements AttributeAccessor {

    public static final String NAME = "context.name";

    public static final String BUSINESS_PARAMS = "context.params";

    public static final String EXECUTOR_ID = "context.executorId";

    public static final String EXECUTOR_CONFIG = "context.executorConfig";

    public static final String RETRY_CONFIG = "context.retryConfig";
}