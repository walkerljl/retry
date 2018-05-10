package org.walkerljl.retry.log.invocation;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Information of invocation
 *
 * @author xingxun
 * @Date 2016/11/25
 */
public class InvocationInfo<RESULT> implements Serializable {

    /** State:success*/
    public static final int STATE_SUCCESS = 1;
    /** State:failure*/
    public static final int STATE_FAILURE = 0;

    /** Application name*/
    private String   appName;
    /** Object class*/
    private Class<?> objectClass;
    /** Method name*/
    private String   methodName;
    /** List of parameter*/
    private Object[] params;
    /** Data of result*/
    private RESULT   resultData;
    /** Data of direct result*/
    private Object   directResultData;
    /** Description*/
    private String   description;
    /** State*/
    private int state;
    /** Throable*/
    private Throwable throwable;
    /** Begin time*/
    private long beginTime = System.currentTimeMillis();
    /** End time*/
    private long endTime   = 0;
    /** trace id*/
    private String traceId;
    /** Map of parameter*/
    private Map<String, Object> parametersMap = new HashMap<String, Object>(0);

    public InvocationInfo(Class<?> objectClass, String methodName, Object[] params, Object directResultData, RESULT resultData,
                          boolean isSuccess) {
        this.objectClass = objectClass;
        this.methodName = methodName;
        this.params = params;
        this.directResultData = directResultData;
        this.resultData = resultData;
        this.state = (isSuccess ? STATE_SUCCESS : STATE_FAILURE);
    }

    public InvocationInfo(Class<?> objectClass, String methodName, Object[] params, boolean isSuccess) {
        this(objectClass, methodName, params, null, null, isSuccess);
    }

    public InvocationInfo(Class<?> objectClass, String methodName, Object[] params) {
        this(objectClass, methodName, params, null, null, true);
    }

    public InvocationInfo(Class<?> objectClass, String methodName) {
        this(objectClass, methodName, null, null, null, true);
    }

    public void setFailure() {
        this.setFailure(null);
    }

    public void setFailure(Throwable throwable) {
        this.state = STATE_FAILURE;
        this.throwable = throwable;
        this.endTime = System.currentTimeMillis();
    }

    public void setSuccess() {
        setSuccess(null, null);
    }

    public void setSuccess(RESULT resultData) {
        setSuccess(resultData, resultData);
    }

    public void setSuccess(Object directResultData, RESULT resultData) {
        setResult(true, directResultData, resultData);
    }

    public void setResult(boolean isSuccess, Object directResultData, RESULT resultData) {
        this.state = (isSuccess ? STATE_SUCCESS : STATE_FAILURE);
        this.endTime = System.currentTimeMillis();
        this.directResultData = directResultData;
        this.resultData = resultData;
    }

    public String getServiceName() {
        return String.format("%s.%s", objectClass.getSimpleName(), methodName);
    }

    public String getTraceInfo() {
        return String.format("serviceName=%s,params=%s,result=%s.", getServiceName(), Arrays.toString(params), directResultData);
    }

    public boolean isSuccess() {
        return STATE_SUCCESS == state;
    }

    /**
     * Get time of cost,unit is ms.
     *
     * @return
     */
    public long getCostTime() {
        return endTime - beginTime;
    }

    /**
     * Add parameter
     *
     * @param key Key of parameter
     * @param value Value of parameter
     */
    public void addParameter(String key, Object value) {
        parametersMap.put(key, value);
    }

    /**
     * Get parameter
     *
     * @param key Key of parameter
     * @return
     */
    public Object getParameter(String key) {
        return parametersMap.get(key);
    }

    //== getter and setter

    /**
     * Getter method for property <tt>appName</tt>.
     *
     * @return property value of appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Setter method for property <tt>appName</tt>.
     *
     * @param appName  value to be assigned to property appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Getter method for property <tt>objectClass</tt>.
     *
     * @return property value of objectClass
     */
    public Class<?> getObjectClass() {
        return objectClass;
    }

    /**
     * Setter method for property <tt>objectClass</tt>.
     *
     * @param objectClass  value to be assigned to property objectClass
     */
    public void setObjectClass(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * Getter method for property <tt>methodName</tt>.
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter method for property <tt>methodName</tt>.
     *
     * @param methodName  value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter method for property <tt>params</tt>.
     *
     * @return property value of params
     */
    public Object[] getParams() {
        return params;
    }

    /**
     * Setter method for property <tt>params</tt>.
     *
     * @param params  value to be assigned to property params
     */
    public void setParams(Object[] params) {
        this.params = params;
    }

    /**
     * Getter method for property <tt>resultData</tt>.
     *
     * @return property value of resultData
     */
    public RESULT getResultData() {
        return resultData;
    }

    /**
     * Setter method for property <tt>resultData</tt>.
     *
     * @param resultData  value to be assigned to property resultData
     */
    public void setResultData(RESULT resultData) {
        this.resultData = resultData;
    }

    /**
     * Getter method for property <tt>directResultData</tt>.
     *
     * @return property value of directResultData
     */
    public Object getDirectResultData() {
        return directResultData;
    }

    /**
     * Setter method for property <tt>directResultData</tt>.
     *
     * @param directResultData  value to be assigned to property directResultData
     */
    public void setDirectResultData(Object directResultData) {
        this.directResultData = directResultData;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property <tt>description</tt>.
     *
     * @param description  value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for property <tt>state</tt>.
     *
     * @return property value of state
     */
    public int getState() {
        return state;
    }

    /**
     * Setter method for property <tt>state</tt>.
     *
     * @param state  value to be assigned to property state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Getter method for property <tt>throwable</tt>.
     *
     * @return property value of throwable
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * Setter method for property <tt>throwable</tt>.
     *
     * @param throwable  value to be assigned to property throwable
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * Getter method for property <tt>beginTime</tt>.
     *
     * @return property value of beginTime
     */
    public long getBeginTime() {
        return beginTime;
    }

    /**
     * Setter method for property <tt>beginTime</tt>.
     *
     * @param beginTime  value to be assigned to property beginTime
     */
    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime  value to be assigned to property endTime
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter method for property <tt>traceId</tt>.
     *
     * @return property value of traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * Setter method for property <tt>traceId</tt>.
     *
     * @param traceId  value to be assigned to property traceId
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * Getter method for property <tt>parametersMap</tt>.
     *
     * @return property value of parametersMap
     */
    public Map<String, Object> getParametersMap() {
        return parametersMap;
    }

    /**
     * Setter method for property <tt>parametersMap</tt>.
     *
     * @param parametersMap  value to be assigned to property parametersMap
     */
    public void setParametersMap(Map<String, Object> parametersMap) {
        this.parametersMap = parametersMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
