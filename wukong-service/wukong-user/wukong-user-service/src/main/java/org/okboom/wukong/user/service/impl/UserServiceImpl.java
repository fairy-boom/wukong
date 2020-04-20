package org.okboom.wukong.user.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.user.dto.UserInfoDTO;
import org.okboom.wukong.user.dto.UserRegisterDTO;
import org.okboom.wukong.user.manager.UserManager;
import org.okboom.wukong.user.service.UserService;

/**
 * @author tookbra
 */
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserManager userManager;

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

    }

    @Override
    public UserInfoDTO findByAccount(String account) {
        return null;
    }
}
