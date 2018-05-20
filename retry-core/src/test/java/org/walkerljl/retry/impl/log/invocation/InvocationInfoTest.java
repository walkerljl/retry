package org.walkerljl.retry.impl.log.invocation;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * InvocationInfoTest
 *
 * @author xingxun
 */
public class InvocationInfoTest {

    private Class<?>  objectClass      = InvocationInfoTest.class;
    private String    methodName       = "test";
    private Object[]  params           = new Object[] {"1"};
    private String    directResultData = "directResultData";
    private String    resultData       = "resultData";
    private Throwable throwable        = new RuntimeException();

    @Test
    public void constructor() {
        InvocationInfo<Object[], String> invocationInfo = new InvocationInfo<>(objectClass, methodName, params, directResultData, resultData,
                true);
        assertAll(invocationInfo, true);

        invocationInfo = new InvocationInfo<>(objectClass, methodName, params, directResultData, resultData, false);
        assertAll(invocationInfo, false);

        invocationInfo = new InvocationInfo<>(objectClass, methodName, params, true);
        assertBase(invocationInfo, true);
        Assert.assertNull(invocationInfo.getResultData());
        Assert.assertNull(invocationInfo.getDirectResultData());

        invocationInfo = new InvocationInfo<>(objectClass, methodName, params, false);
        assertBase(invocationInfo, false);
        Assert.assertNull(invocationInfo.getResultData());
        Assert.assertNull(invocationInfo.getDirectResultData());

        invocationInfo = new InvocationInfo<>(objectClass, methodName, params);
        assertBase(invocationInfo, false);
        Assert.assertNull(invocationInfo.getResultData());
        Assert.assertNull(invocationInfo.getDirectResultData());
    }

    @Test
    public void costTime() {
        InvocationInfo<Object[], String> actual = new InvocationInfo<>(objectClass, methodName, params);
        long costTime = actual.getEndTime() - actual.getBeginTime();
        Assert.assertEquals(costTime, actual.getCostTime());
    }

    @Test
    public void setSuccess() {
        InvocationInfo<Object[],String> actual = new InvocationInfo<>(objectClass, methodName, params);
        actual.markSuccess();
        Assert.assertTrue(actual.isSuccess());

        actual.markSuccess(resultData);
        Assert.assertTrue(actual.isSuccess());
        Assert.assertEquals(resultData, actual.getResultData());

        actual.markSuccess(directResultData, resultData);
        Assert.assertTrue(actual.isSuccess());
        Assert.assertEquals(resultData, actual.getResultData());
        Assert.assertEquals(directResultData, actual.getDirectResultData());
    }

    @Test
    public void setFailure() {
        InvocationInfo<Object[], String> actual = new InvocationInfo<>(objectClass, methodName, params);
        actual.markFailure();
        Assert.assertFalse(actual.isSuccess());

        actual.markFailure(throwable);
        Assert.assertFalse(actual.isSuccess());
        Assert.assertEquals(throwable, actual.getThrowable());
    }

    @Test
    public void setResult() {
        InvocationInfo<Object[], String> actual = new InvocationInfo<>(objectClass, methodName, params);
        actual.markResult(true, directResultData, resultData);
        Assert.assertTrue(actual.isSuccess());
        Assert.assertEquals(directResultData, actual.getDirectResultData());
        Assert.assertEquals(resultData, actual.getResultData());

        actual.markResult(false, directResultData, resultData);
        Assert.assertFalse(actual.isSuccess());
        Assert.assertEquals(directResultData, actual.getDirectResultData());
        Assert.assertEquals(resultData, actual.getResultData());
    }

    @Test
    public void isSuccess() {
        InvocationInfo<Object[], String> actual = new InvocationInfo<>(objectClass, methodName, params);
        Assert.assertFalse(actual.isSuccess());

        actual = new InvocationInfo<>(objectClass, methodName, params, true);
        Assert.assertTrue(actual.isSuccess());
    }

    @Test
    public void addAndGetParameter() {
        InvocationInfo<Object[], String> actual = new InvocationInfo<>(objectClass, methodName, params);
        actual.addParameter("key1", "value1");

        Assert.assertEquals("value1", actual.getParameter("key1"));
    }

    private void assertBase(InvocationInfo<Object[], String> actual, boolean isSuccess) {
        if (isSuccess) {
            Assert.assertTrue(actual.isSuccess());
        } else {
            Assert.assertFalse(actual.isSuccess());
        }
        Assert.assertEquals(objectClass, actual.getObjectClass());
        Assert.assertEquals(methodName, actual.getMethodName());
        Assert.assertEquals(params, actual.getParam());
    }

    private void assertAll(InvocationInfo<Object[], String> actual, boolean isSuccess) {
        assertBase(actual, isSuccess);
        Assert.assertEquals(directResultData, actual.getDirectResultData());
        Assert.assertEquals(resultData, actual.getResultData());
    }
}