package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppConnectMessage extends Message {

    public CmppConnectMessage() {
        super(CmppCommand.CONNECT, 12);
    }

    /**
     * 源地址，此处为SP_Id，即SP 的企业 代码。
     */
    private String sourceAddr;

    /**
     * 用于鉴别源地址。其值通过单向MD5 hash计算得出，
     * 表示如下： AuthenticatorSource = MD5 （ Source_Addr+9 字 节 的 0 +shared secret+timestamp）
     * Shared secret 由中国移动与源地址实 体事 先商定 ， timestamp 格式 为： MMDDHHMMSS，即月日时分秒，10 位。
     */
    private byte [] authenticatorSource;

    /**
     * 双方协商的版本号(高位 4bit 表示主 版本号,低位 4bit 表示次版本号)，对 于3.0的版本，高4bit为 3，低4 位为 0
     */
    private short version;

    /**
     * 时间戳的明文,由客户端产生,格式为 MMDDHHMMSS，即月日时分秒，10 位数字的整型，右对齐
     */
    private Integer timestamp;


}
