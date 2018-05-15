package org.walkerljl.retry.standard.abstracts;

import org.walkerljl.retry.exception.resouce.CannotDestroyResourceException;
import org.walkerljl.retry.exception.resouce.CannotInitResourceException;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.util.LoggerUtil;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.standard.Resource;
import org.walkerljl.retry.standard.ResourceRepository;

/**
 * AbstractResource
 *
 * @author xingxun
 * @Date 2016/12/9
 */
public abstract class AbstractResource implements Resource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractResource.class);

    private volatile boolean inited = false;

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getInstanceId() {
        return this.toString();
    }

    @Override
    public void init() throws CannotInitResourceException {
        long startTime = System.currentTimeMillis();
        try {
            if (LOGGER.isInfoEnabled()) {
                LoggerUtil.info(LOGGER, String.format("%s is initing.", getServerName()));
            }
            if (!inited) {
                synchronized (this) {
                    processInit();
                    ResourceRepository.register(getGroup(), getId(), this);
                    inited = true;
                    if (LOGGER.isInfoEnabled()) {
                        LoggerUtil.info(LOGGER, String.format("%s has inited,consume %s milliseconds.", getServerName(),
                                (System.currentTimeMillis() - startTime)));
                    }
                }
            }
        } catch (Throwable e) {
            LoggerUtil.error(LOGGER, e, String.format("%s occurs some erros when initing.", getServerName()));
            throw new CannotInitResourceException(e);
        }
    }

    @Override
    public void destroy() throws CannotDestroyResourceException {
        long startTime = System.currentTimeMillis();
        try {
            if (LOGGER.isInfoEnabled()) {
                LoggerUtil.info(LOGGER, String.format("%s is destroying.", getServerName()));
            }
            synchronized (this) {
                processDestroy();
                ResourceRepository.unregister(getGroup(), getId());
                inited = false;
                if (LOGGER.isInfoEnabled()) {
                    LoggerUtil.info(LOGGER, String.format("%s has destroied,consume %s milliseconds.", getServerName(),
                            (System.currentTimeMillis() - startTime)));
                }
            }
        } catch (Throwable e) {
            LoggerUtil.error(LOGGER, e, String.format("%s occurs some erros when destroying.", getServerName()));
            throw new CannotDestroyResourceException(e);
        }
    }

    /**
     * Get service name
     *
     * @return
     */
    protected String getServerName() {
        return String.format("Resource[id=%s,group=%s,instanceId=%s]", getId(), getGroup(), getInstanceId());
    }

    public abstract void processInit() throws CannotInitResourceException;

    public abstract void processDestroy() throws CannotDestroyResourceException;
}
