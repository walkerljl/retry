package org.walkerljl.retry.impl.defaults;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.impl.AttributeAccessor;

/**
 * DefaultAttributeAccessorTest
 *
 * @author xingxun
 * @Date 2018/5/20
 */
public class DefaultAttributeAccessorTest extends BaseUnitTest {

    @Test
    public void test() {

        AttributeAccessor attributeAccessor = new DefaultAttributeAccessor();

        String expectedName = "userName";
        String expectedValue = "xingxun";

        attributeAccessor.setAttribute(expectedName, expectedValue);
        Assert.assertTrue(attributeAccessor.hasAttribute(expectedName));
        Assert.assertEquals(attributeAccessor.getAttribute(expectedName), expectedValue);
        Assert.assertEquals(attributeAccessor.attributeNames()[0], expectedName);

        AttributeAccessor copiedAttributeAccessor = new DefaultAttributeAccessor();
        copiedAttributeAccessor.copy(attributeAccessor);

        Assert.assertTrue(copiedAttributeAccessor.hasAttribute(expectedName));
        Assert.assertEquals(copiedAttributeAccessor.getAttribute(expectedName), expectedValue);
        Assert.assertEquals(copiedAttributeAccessor.attributeNames()[0], expectedName);

        attributeAccessor.removeAttribute(expectedName);
        Assert.assertFalse(attributeAccessor.hasAttribute(expectedName));

        Assert.assertFalse(attributeAccessor.equals(copiedAttributeAccessor));
    }
}
