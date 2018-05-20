package org.walkerljl.retry.impl;

/**
 * 常量
 *
 * @author xingxun
 */
public class RetryConstants {

    /** 组件标识符：任务加载器*/
    public static final String COMPONENT_IDENTIFIER_JOB_LOADER     = "RetryJobLoder";
    /** 组件标识符：任务分发器*/
    public static final String COMPONENT_IDENTIFIER_JOB_DISPATCHER = "RetryJobDispatcher";
    /** 组件标识符：任务分执行器*/
    public static final String COMPONENT_IDENTIFIER_JOB_EXECUTOR   = "RetryJobExecutor";
    /** 组件标识符：重试Broker*/
    public static final String COMPONENT_IDENTIFIER_RETRY_BROKER   = "RetryBroker";
    /** 组件标识符：重试服务器*/
    public static final String COMPONENT_IDENTIFIER_RETRY_SERVER   = "RetryServer";

    /** 默认的组件ID：重试Broker*/
    public static final String DEFAULT_COMPONENT_ID_RETRY_BROKER   = "default";
    /** 默认的组件ID：重试服务器*/
    public static final String DEFAULT_COMPONENT_ID_RETRY_SERVER   = "default";

    public static final int    LOAD_PAGE_STEP = 1;

}