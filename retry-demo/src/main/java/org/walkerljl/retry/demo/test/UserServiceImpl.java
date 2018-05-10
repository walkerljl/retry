package org.walkerljl.retry.demo.test;

import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 *
 * @author xingxun
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public void add(User user) {

        LOGGER.info(String.format("Add one user:%s.", user.toString()));
    }
}