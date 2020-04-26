package org.okboom.wukong.user.manager;

import org.okboom.wukong.user.domain.UserInfo;

/**
 * @author tookbra
 */
public interface UserManager {

    /**
     * 保存用户
     * @param user
     * @return
     */
    boolean save(UserInfo user);

    /**
     * 判断账号是否存在
     * @param account
     * @return
     */
    boolean exists(String account);

    /**
     * 查询用户信息
     * @param account
     * @return
     */
    UserInfo findByMobile(String account);
}
