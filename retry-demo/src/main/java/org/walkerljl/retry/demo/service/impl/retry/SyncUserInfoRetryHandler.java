package org.walkerljl.retry.demo.service.impl.retry;

import java.util.List;

import org.walkerljl.retry.RetryHandler;
import org.walkerljl.retry.db.util.JSONUtil;
import org.walkerljl.retry.demo.service.UserService;
import org.walkerljl.retry.demo.service.impl.UserServiceImpl;
import org.walkerljl.retry.impl.RetryContext;
import org.walkerljl.retry.impl.log.logger.LoggerFactory;
import org.walkerljl.retry.logger.Logger;
import org.walkerljl.retry.model.RetryParam;

/**
 *
 * @author xingxun
 */
public class SyncUserInfoRetryHandler implements RetryHandler {

    private UserService userService = new UserServiceImpl();

    @Override
    public void retry(RetryContext context) {
        //throw new AppServiceException("执行错误");

        List<RetryParam> retryParams = (List<RetryParam>) context.getAttribute(RetryContext.BUSINESS_PARAMS);
        String userJSONString = retryParams.get(0).getValue();
        //User user = JSONUtil.parseObject(userJSONString, User.class);

        Logger logger = LoggerFactory.getLogger(getClass());
        if (logger.isInfoEnabled()) {
            logger.info(JSONUtil.toJSONString(userJSONString));
        }
    }
}