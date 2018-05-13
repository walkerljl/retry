package org.walkerljl.retry.impl;

/**
 * 属性Accessor
 *
 * @author xingxun
 */
public interface AttributeAccessor {

    /**
     * 设置属性
     *
     * @param name 属性名称
     * @param value 属性值
     */
    void setAttribute(String name, Object value);

    /**
     * 获取属性
     *
     * @param name 属性名称
     * @return
     */
    Object getAttribute(String name);

    /**
     * 删除属性
     *
     * @param name 属性名称
     * @return
     */
    Object removeAttribute(String name);

    /**
     * 判断是否有指定属性
     *
     * @param name 属性名称
     * @return
     */
    boolean hasAttribute(String name);

    /**
     * 复制属性
     *
     * @param source
     */
    void copy(AttributeAccessor source);

    /**
     * 获取所有属性名称
     *
     * @return
     */
    String[] attributeNames();
}