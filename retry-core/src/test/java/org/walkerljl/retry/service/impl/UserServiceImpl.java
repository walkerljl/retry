package org.walkerljl.retry.service.impl;

import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.service.User;
import org.walkerljl.retry.service.UserService;

/**
 *
 * @author xingxun
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void save(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Save user:%s.", user.toString()));
        }
    }

    @Override
    public void sync(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Sync user:%s.", user.toString()));
        }
    }
}