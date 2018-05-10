package org.walkerljl.retry.demo.impl.defaults;

import org.walkerljl.retry.log.logger.Logger;
import org.walkerljl.retry.log.logger.LoggerRepository;
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