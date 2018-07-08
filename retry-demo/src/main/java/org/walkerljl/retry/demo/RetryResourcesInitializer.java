package org.walkerljl.retry.demo;

import org.walkerljl.retry.demo.defaults.DefaultLoggerRepository;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.logger.LoggerRepository;
import org.walkerljl.retry.standard.resource.Resource;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 *
 * @author xingxun
 */
public class RetryResourcesInitializer implements Resource {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getInstanceId() {
        return null;
    }

    @Override
    public void init() throws CannotInitResourceException {

        LoggerRepository loggerRepository = new DefaultLoggerRepository();
        LoggerFactory.bindLoggerRepository(loggerRepository);

    }

    @Override
    public void destroy() throws CannotDestroyResourceException {

    }
}