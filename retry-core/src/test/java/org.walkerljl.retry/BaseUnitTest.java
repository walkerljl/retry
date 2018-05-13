package org.walkerljl.retry;

import org.junit.After;
import org.junit.Before;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;

/**
 *
 * 单元测试基类
 *
 * @author lijunlin
 */
public class BaseUnitTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseUnitTest.class);

    @Before
    public void before() {
        LOGGER.debug("Initialized some resoruces.");
    }

    @After
    public void after() {
        LOGGER.debug("Released some resources.");
    }
}