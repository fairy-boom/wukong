package org.okboom.wukong.user.service;

import org.okboom.wukong.dubbo.result.ServiceResult;
import org.okboom.wukong.user.dto.UserInfoDTO;
import org.okboom.wukong.user.dto.UserRegisterDTO;

import javax.validation.constraints.NotNull;

/**
 * @author tookbra
 */
public interface UserService {

    /**
     * 判断账号是否存在
     * @param account 账号
     * @return
     */
    ServiceResult exists(@NotNull String account);

    /**
     *
     * @param account
     */
    void captchaSend(String account);

    /**
     * 注册
     * @param userRegisterDTO
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 查询用户信息
     * @param mobile 手机号
     * @return
     */
    UserInfoDTO findByMobile(String mobile);
}
