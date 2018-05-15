package org.walkerljl.retry.demo.service.impl;

import org.walkerljl.retry.demo.service.User;
import org.walkerljl.retry.demo.service.UserService;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.logger.Logger;

/**
 *
 * @author xingxun
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void save(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Add one user:%s.", user.toString()));
        }
    }
}