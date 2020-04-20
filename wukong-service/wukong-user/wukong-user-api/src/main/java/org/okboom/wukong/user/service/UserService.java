package org.okboom.wukong.user.service;

import org.okboom.wukong.user.dto.UserInfoDTO;
import org.okboom.wukong.user.dto.UserRegisterDTO;

/**
 * @author tookbra
 */
public interface UserService {

    /**
     * 注册
     * @param userRegisterDTO
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 查询用户信息
     * @param account 账号
     * @return
     */
    UserInfoDTO findByAccount(String account);
}
