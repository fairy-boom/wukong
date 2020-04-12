package org.okboom.wukong.sms.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
public class ChannelInfo implements Serializable {
    private static final long serialVersionUID = 1816583945610742196L;

    /**
     * 网关地址
     */
    private String host;

    /**
     * 网关端口
     */
    private Integer port;

    /**
     * sp号码
     */
    private String spNum;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginSecret;

    /**
     * 限流
     */
    private Integer limit;

    /**
     * 备注
     */
    private String description;
}
