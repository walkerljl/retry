package org.walkerljl.retry.service;

/**
 *
 * @author xingxun
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 同步用户
     *
     * @param user
     */
    void sync(User user);
}