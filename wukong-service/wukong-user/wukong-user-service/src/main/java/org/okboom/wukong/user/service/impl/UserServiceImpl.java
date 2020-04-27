package org.okboom.wukong.user.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.okboom.wukong.dubbo.result.ServiceResult;
import org.okboom.wukong.user.domain.UserInfo;
import org.okboom.wukong.user.dto.UserInfoDTO;
import org.okboom.wukong.user.dto.UserRegisterDTO;
import org.okboom.wukong.user.manager.UserManager;
import org.okboom.wukong.user.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author tookbra
 */
@Slf4j
@AllArgsConstructor
@RestController
@Service(validation = "true", version = "${dubbo.provider.UserService.version}", timeout = 1000, parameters = {"metadata", "remote"})
public class UserServiceImpl implements UserService {

    private final UserManager userManager;

    @Override
    public ServiceResult exists(@NotNull String account) {
        boolean exists = userManager.exists(account);
        if(exists) {
            return ServiceResult.fail("账号已存在");
        }
        return ServiceResult.success();
    }

    @Override
    public void captchaSend(String account) {

    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

    }

    @Override
    public UserInfoDTO findByMobile(String mobile) {
        UserInfo userInfo = userManager.findByMobile(mobile);
        return null;
    }
}
