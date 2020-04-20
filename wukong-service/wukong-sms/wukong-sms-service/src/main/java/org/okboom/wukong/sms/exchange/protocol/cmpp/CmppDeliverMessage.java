package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppDeliverMessage extends Message {
    private static final long serialVersionUID = -4941563962661310259L;

    public CmppDeliverMessage() {
        super(CmppCommand.DELIVER, 12);
    }

    /**
     * 信息标识
     */
    private Long msgId;
    /**
     * 目的号码。 SP的服务代码，或者是前缀为服务 代码的长号码；该号码是手机用户 短消息的被叫号码。
     */
    private String destId;
    /**
     * 业务标识，是数字、字母和符号的 组合
     */
    private String serviceId;
    /**
     *  0是普通GSM类型，点到点方式 ,127 :写sim卡
     */
    private short tpPid = 0;
    /**
     * 0:msgContent不带协议头。1:带有协议头
     */
    private short tpUdHi = 0;
    /**
     * 信息格式： 0：ASCII 串； 3：短信写卡操作； 4：二进制信息； 8：UCS2 编码； 15：含GB 汉 字 。。。。。。
     */
    private short msgFmt = 0;
    /**
     *  源终端MSISDN 号码（状态报告时 填为CMPP_SUBMIT 消息的目的终 端号码）。
     */
    private String srcTerminalId;
    /**
     * 接收短信的用户的号码类型，0：真实号 码；1：伪码
     */
    private short srcTerminalType = 0;
    /**
     * 是否为状态报告： 0：非状态报告； 1：状态报告。
     */
    private short registeredDelivery = 0;
    /**
     * 消息长度，取值大于或等于0
     */
    private short msgLength = 140;
    /**
     * 消息内容。
     */
    private byte[] msgContent;
    /**
     * 点播业务使用的 LinkID，非点播类 业务的MT 流程不使用该字段。
     */
    private String linkId;
}
