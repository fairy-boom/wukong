package org.okboom.wukong.user.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -8274936325908283394L;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
