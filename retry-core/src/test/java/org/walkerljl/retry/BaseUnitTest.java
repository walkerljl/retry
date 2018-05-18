package org.walkerljl.retry;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.logger.DefaultLoggerRepository;
import org.walkerljl.retry.logger.LoggerRepository;

/**
 *
 * 单元测试基类
 *
 * @author xingxun
 */
public class BaseUnitTest {

    @BeforeClass
    public void beforeClass() {
        LoggerRepository loggerRepository = new DefaultLoggerRepository();
        LoggerFactory.bindLoggerRepository(loggerRepository);
    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {
    }
}