package org.okboom.wukong.user.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户注册DTO
 * @author tookbra
 */
@Data
@ToString
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID = -1301785232931078751L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 验证码
     */
    private String code;
    /**
     * 密码
     */
    private String password;
}
