package org.walkerljl.retry.standard;

import org.walkerljl.retry.exception.resouce.CannotDestroyResourceException;
import org.walkerljl.retry.exception.resouce.CannotInitResourceException;

/**
 * Describe one kind of resource which can init and destroy.
 *
 * @author: xingxun
 */
public interface Resource extends Identifer {

    /**
     * Get instance id
     *
     * @return
     */
    String getInstanceId();

    /**
     * Init
     *
     * @throws CannotInitResourceException
     */
    void init() throws CannotInitResourceException;

    /**
     * Destroy
     *
     * @throws CannotDestroyResourceException
     */
    void destroy() throws CannotDestroyResourceException;
}
