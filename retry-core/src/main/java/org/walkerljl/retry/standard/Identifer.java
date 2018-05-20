package org.walkerljl.retry.standard;

/**
 * 描述一个具有ID、名称、分组标识符的对象
 *
 * @author: xingxun
 */
public interface Identifer {

    /**
     * 获取ID
     *
     * @return
     */
    String getId();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

    /**
     * 获取分组
     *
     * @return
     */
    String getGroup();
}
