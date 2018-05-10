package org.walkerljl.retry.standard;

/**
 * Describe one kind of object which has id,name and group
 *
 * @author: xingxun
 */
public interface Identifer {

    /**
     * Get id
     *
     * @return
     */
    String getId();

    /**
     * Get name
     *
     * @return
     */
    String getName();

    /**
     * Get groud
     *
     * @return
     */
    String getGroup();
}
