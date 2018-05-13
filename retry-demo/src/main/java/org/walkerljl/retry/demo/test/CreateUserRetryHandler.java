package org.walkerljl.retry.demo.test;

import java.util.List;

import org.walkerljl.retry.RetryHandler;
import org.walkerljl.retry.db.util.JSONUtil;
import org.walkerljl.retry.model.RetryParam;
import org.walkerljl.retry.impl.RetryContext;

/**
 *
 * @author xingxun
 */
public class CreateUserRetryHandler implements RetryHandler {

    private UserService userService = new UserServiceImpl();

    public void retry(RetryContext context) {
        List<RetryParam> retryParams = (List<RetryParam>) context.getAttribute(RetryContext.BUSINESS_PARAMS);
        User user = JSONUtil.parseObject(retryParams.get(0).getValue(), User.class);
        userService.add(user);
    }
}