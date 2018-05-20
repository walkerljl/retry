package org.walkerljl.retry.impl.util;

/**
 * ThrowableUtil
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class ThrowableUtil {

    /**
     * 获取异常消息
     *
     * @param e
     * @return
     */
    public static String getMessage(Throwable e) {
        return e == null ? null : e.getMessage();
    }
}
