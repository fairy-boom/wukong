package org.okboom.wukong.user.manager;

import org.okboom.wukong.user.domain.User;

/**
 * @author tookbra
 */
public interface UserManager {

    /**
     * 保存用户
     * @param user
     * @return
     */
    boolean save(User user);

    /**
     * 查询用户信息
     * @param account
     * @return
     */
    User findByAccount(String account);
}
