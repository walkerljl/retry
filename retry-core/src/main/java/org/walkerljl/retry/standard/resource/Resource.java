package org.walkerljl.retry.standard.resource;

import org.walkerljl.retry.standard.Identifer;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 * 描述一种可初始化和销毁的资源
 *
 * @author: xingxun
 */
public interface Resource extends Identifer {

    /**
     * 返回实例ID
     *
     * @return 实例ID
     */
    String getInstanceId();

    /**
     * 初始化资源
     *
     * @throws CannotInitResourceException
     */
    void init() throws CannotInitResourceException;

    /**
     * 销毁资源
     *
     * @throws CannotDestroyResourceException
     */
    void destroy() throws CannotDestroyResourceException;
}
