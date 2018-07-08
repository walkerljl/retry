package org.walkerljl.retry.standard.resource.abstracts;

import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.impl.log.logger.LoggerNames;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.standard.resource.Resource;
import org.walkerljl.retry.standard.resource.ResourceRepository;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 * 抽象的资源
 *
 * @author xingxun
 * @Date 2016/12/9
 */
public abstract class AbstractResource implements Resource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerNames.RETRY);

    /** 是否初始化标志*/
    private volatile boolean inited = false;

    /***
     * 处理初始化
     *
     * @throws CannotInitResourceException
     */
    public abstract void processInit() throws CannotInitResourceException;

    /**
     * 处理销毁
     *
     * @throws CannotDestroyResourceException
     */
    public abstract void processDestroy() throws CannotDestroyResourceException;

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
                LOGGER.info(String.format("%s is initing.", getServerName()));
            }
            if (!inited) {
                synchronized (this) {
                    if (!inited) {
                        processInit();
                        ResourceRepository.register(getGroup(), getId(), this);
                        inited = true;
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has inited,consume %s milliseconds.", getServerName(), (System.currentTimeMillis() - startTime)));
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when initing.", getServerName()), e);
            throw new CannotInitResourceException(e);
        }
    }

    @Override
    public void destroy() throws CannotDestroyResourceException {
        long startTime = System.currentTimeMillis();
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("%s is destroying.", getServerName()));
            }
            if (inited) {
                synchronized (this) {
                    if (inited) {
                        processDestroy();
                        ResourceRepository.unregister(getGroup(), getId());
                        inited = false;
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has destroied,consume %s milliseconds.", getServerName(),
                                    (System.currentTimeMillis() - startTime)));
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when destroying.", getServerName()), e);
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
}
