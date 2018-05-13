package org.walkerljl.retry.impl.log.logger;

import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.logger.LoggerRepository;

/**
 * LoggerFactory
 *
 * @author xingxun
 */
public class LoggerFactory {

    private static LoggerRepository loggerRepository;

    public static Logger getLogger(Class<?> clazz) {
        return loggerRepository.getLogger(clazz);
    }

    public static Logger getLogger(String name) {
        return loggerRepository.getLogger(name);
    }

    public static void bindLoggerRepository(LoggerRepository loggerRepository) {
        LoggerFactory.loggerRepository = loggerRepository;
    }
}