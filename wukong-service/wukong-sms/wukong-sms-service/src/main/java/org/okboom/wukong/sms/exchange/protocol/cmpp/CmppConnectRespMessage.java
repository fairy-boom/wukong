package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@NoArgsConstructor
public class CmppConnectRespMessage extends Message {

    /**
     * 状态 0：正确 1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误
     */
    private long status;

    /**
     * ISMG认证码，用于鉴别 ISMG。 其值通过单向 MD5 hash 计算得出，
     * 表示如下： AuthenticatorISMG =MD5（Status+AuthenticatorSource +shared secret），
     * Shared secret 由中 国移动与源地址实体事先商定，
     * AuthenticatorSource 为源地址实体 发 送 给 ISMG 的 对 应 消 息 CMPP_Connect中的值。 认证出错时，此项为空
     */
    private String authenticatorSource;

    /**
     * 服务器支持的最高版本号，对于 3.0 的版本，高4bit 为3，低 4位为 0
     */
    private short version;

}
