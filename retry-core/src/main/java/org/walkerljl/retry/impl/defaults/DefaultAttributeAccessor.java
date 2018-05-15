/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package org.walkerljl.retry.impl.defaults;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.walkerljl.retry.impl.AttributeAccessor;
import org.walkerljl.retry.impl.util.AssertUtil;
import org.walkerljl.retry.impl.util.StringUtil;

/**
 * DefaultAttributeAccessor
 *
 * @author xingxun
 */
public class DefaultAttributeAccessor implements AttributeAccessor, Serializable {

    private static final long serialVersionUID = 4409101753242541792L;

    private final Map<String, Object> attributes = new LinkedHashMap(0);

    @Override
    public void setAttribute(String name, Object value) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        if (value != null) {
            this.attributes.put(name, value);
        } else {
            this.removeAttribute(name);
        }
    }

    @Override
    public Object getAttribute(String name) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        return this.attributes.get(name);
    }

    @Override
    public Object removeAttribute(String name) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        return this.attributes.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        AssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        return this.attributes.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        return this.attributes.keySet().toArray(new String[this.attributes.size()]);
    }

    @Override
    public void copy(AttributeAccessor source) {
        AssertUtil.assertParam(source != null, "source");
        String[] attributeNames = source.attributeNames();
        String[] var3 = attributeNames;
        int var4 = attributeNames.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String attributeName = var3[var5];
            this.setAttribute(attributeName, source.getAttribute(attributeName));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        DefaultAttributeAccessor that = (DefaultAttributeAccessor) o;

        return attributes != null ? attributes.equals(that.attributes) : that.attributes == null;
    }

    @Override
    public int hashCode() {
        return attributes != null ? attributes.hashCode() : 0;
    }
}