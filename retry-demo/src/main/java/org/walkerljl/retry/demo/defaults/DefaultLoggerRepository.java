package org.walkerljl.retry.demo.defaults;

import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.logger.LoggerRepository;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 *
 * @author xingxun
 */
public class DefaultLoggerRepository implements LoggerRepository {

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new DefaultLogger(LoggerFactory.getLogger(clazz));
    }

    @Override
    public Logger getLogger(String name) {
        return new DefaultLogger(LoggerFactory.getLogger(name));
    }
}