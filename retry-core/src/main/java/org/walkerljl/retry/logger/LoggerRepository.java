package org.walkerljl.retry.logger;

/**
 * LoggerRepository
 *
 * @author xingxun
 */
public interface LoggerRepository {

    /**
     * Get logger by class
     *
     * @param clazz
     * @return
     */
    Logger getLogger(Class<?> clazz);

    /**
     * Get logger by name
     *
     * @param name
     * @return
     */
    Logger getLogger(String name);
}