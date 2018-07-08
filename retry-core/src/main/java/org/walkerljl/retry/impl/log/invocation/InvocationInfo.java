package org.walkerljl.retry.impl.log.invocation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.walkerljl.retry.model.BaseEntity;

/**
 * 调用信息
 *
 * @author xingxun
 * @Date 2016/11/25
 */
public class InvocationInfo<PARAM, RESULT> extends BaseEntity {

    /** 状态:成功*/
    public static final int STATE_SUCCESS = 1;
    /** 状态:失败*/
    public static final int STATE_FAILURE = 0;

    /** 应用名称*/
    private String   appName;
    /** 对象Class*/
    private Class<?> objectClass;
    /** 方法名称*/
    private String   methodName;
    /** 参数*/
    private PARAM    param;
    /** 结果数据*/
    private RESULT   resultData;
    /** 原始结果数据*/
    private Object   directResultData;
    /** 描述*/
    private String   description;
    /** 状态*/
    private int state = STATE_SUCCESS;
    /** 异常对象*/
    private Throwable throwable;
    /** 开始时间*/
    private long beginTime = System.currentTimeMillis();
    /** 结束时间*/
    private long endTime   = 0;
    /** trace id*/
    private String traceId;
    /** 参数Map*/
    private Map<String, Object> parametersMap = new LinkedHashMap<>(0);

    /**
     * 构造函数
     *
     * @param objectClass 调用对象Class
     * @param methodName 方法名称
     * @param param 参数
     * @param directResultData 直接(原始)结果数据
     * @param resultData 结果数据
     * @param isSuccess 调用是否成功(true:成功,false:失败)
     */
    public InvocationInfo(Class<?> objectClass, String methodName, PARAM param, Object directResultData, RESULT resultData,
                          boolean isSuccess) {
        this.objectClass = objectClass;
        this.methodName = methodName;
        this.param = param;
        this.directResultData = directResultData;
        this.resultData = resultData;
        this.state = (isSuccess ? STATE_SUCCESS : STATE_FAILURE);
    }

    /**
     * 构造函数
     *
     * @param objectClass 调用对象Class
     * @param methodName 方法名称
     * @param param 参数
     * @param isSuccess 调用是否成功(true:成功,false:失败)
     */
    public InvocationInfo(Class<?> objectClass, String methodName, PARAM param, boolean isSuccess) {
        this(objectClass, methodName, param, null, null, isSuccess);
    }

    /**
     * 构造函数
     *
     * @param objectClass 调用对象Class
     * @param methodName 方法名称
     * @param param 参数
     */
    public InvocationInfo(Class<?> objectClass, String methodName, PARAM param) {
        this(objectClass, methodName, param, null, null, false);
    }

    /**
     * 标注失败的调用
     */
    public void markFailure() {
        this.markFailure(null);
    }

    /**
     * 标注失败的调用
     *
     * @param throwable 异常对象
     */
    public void markFailure(Throwable throwable) {
        this.state = STATE_FAILURE;
        this.throwable = throwable;
        this.endTime = System.currentTimeMillis();
    }

    /**
     * 标注成功的调用
     */
    public void markSuccess() {
        markSuccess(null, null);
    }

    /**
     * 标注成功的调用
     *
     * @param resultData 结果数据
     */
    public void markSuccess(RESULT resultData) {
        markSuccess(resultData, resultData);
    }

    /**
     * 标注成功的调用
     *
     * @param directResultData 直接(原始)结果数据
     * @param resultData 结果数据
     */
    public void markSuccess(Object directResultData, RESULT resultData) {
        markResult(true, directResultData, resultData);
    }

    /**
     * 设置结果
     *
     * @param isSuccess  调用是否成功(true:成功,false:失败)
     * @param directResultData 直接(原始)结果数据
     * @param resultData 结果数据
     */
    public void markResult(boolean isSuccess, Object directResultData, RESULT resultData) {
        this.state = (isSuccess ? STATE_SUCCESS : STATE_FAILURE);
        this.endTime = System.currentTimeMillis();
        this.directResultData = directResultData;
        this.resultData = resultData;
    }

    /**
     * 获取服务名称
     *
     * @return 服务名称(类名+方法名称)
     */
    public String getServiceName() {
        return String.format("%s.%s", objectClass.getSimpleName(), methodName);
    }

    /**
     * 获取跟踪信息
     *
     * @return 跟踪信息(服务名称+参数+返回值)
     */
    public String getTraceInfo() {
        return String.format("serviceName=%s,params=%s,result=%s.", getServiceName(), String.valueOf(param), directResultData);
    }

    /**
     * 判断调用是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return STATE_SUCCESS == state;
    }

    /**
     * 获取调用耗时(单位:毫秒)
     *
     * @return
     */
    public long getCostTime() {
        return endTime - beginTime;
    }

    /**
     * 添加参数
     *
     * @param key 参数Key
     * @param value 参数值Value
     */
    public void addParameter(String key, Object value) {
        parametersMap.put(key, value);
    }

    /**
     * 获取参数值
     *
     * @param key 参数Key
     */
    public Object getParameter(String key) {
        return parametersMap.get(key);
    }

    //== getter and setter

    /**
     * 获取应用名称
     *
     * @return
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置应用名称
     *
     * @param appName 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 获取调用对象Class
     * @return
     */
    public Class<?> getObjectClass() {
        return objectClass;
    }

    /**
     * 设置调用对象Class
     *
     * @param objectClass 调用对象Class
     */
    public void setObjectClass(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * 获取方法名称
     *
     * @return
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名称
     *
     * @param methodName 方法名称
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    public PARAM getParam() {
        return param;
    }

    /**
     * 设置参数列表
     *
     * @param param 参数
     *
     */
    public void setParam(PARAM param) {
        this.param = param;
    }

    /**
     * 获取结果数据
     *
     * @return
     */
    public RESULT getResultData() {
        return resultData;
    }

    /**
     * 设置结果数据
     *
     * @param resultData 结果数据
     */
    public void setResultData(RESULT resultData) {
        this.resultData = resultData;
    }

    /**
     * 获取直接(原始)结果数据
     *
     * @return
     */
    public Object getDirectResultData() {
        return directResultData;
    }

    /**
     * 设置直接(原始)结果数据
     *
     * @param directResultData 直接(原始)结果数据
     */
    public void setDirectResultData(Object directResultData) {
        this.directResultData = directResultData;
    }

    /**
     * 获取调用描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置调用描述
     *
     * @param description 调用描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取调用结果状态
     *
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * 设置调用结果状态
     *
     * @param state 结果状态
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取异常对象
     *
     * @return
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * 设置异常对象
     *
     * @param throwable 异常对象
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * 获取调用开始时间
     *
     * @return
     */
    public long getBeginTime() {
        return beginTime;
    }

    /**
     * 设置调用开始时间
     *
     * @param beginTime 调用开始时间
     */
    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取调用结束时间
     *
     * @return
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 调用结束时间
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取trace id
     *
     * @return
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * 设置trace id
     *
     * @param traceId trace id
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * 获取参数Map
     *
     * @return
     */
    public Map<String, Object> getParametersMap() {
        return parametersMap;
    }

    /**
     * 设置参数Map
     *
     * @param parametersMap 参数Map
     */
    public void setParametersMap(Map<String, Object> parametersMap) {
        this.parametersMap = parametersMap;
    }
}
