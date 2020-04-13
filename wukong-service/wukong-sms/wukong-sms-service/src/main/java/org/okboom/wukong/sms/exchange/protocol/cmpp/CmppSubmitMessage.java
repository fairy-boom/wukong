package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppSubmitMessage extends Message {

    public CmppSubmitMessage() {
        super(CmppCommand.SUBMIT, 12);
    }


    /**
     * 信息标识
     */
    private byte [] msgId;
    /**
     * 相同Msg_Id的信息总条数，从1开始
     */
    private short pkTotal = 1;
    /**
     * 相同Msg_Id的信息序号，从1开始
     */
    private short pkNumber = 1;
    /**
     * 是否要求返回状态确认报告： 0：不需要； 1：需要
     */
    private short registeredDelivery = 1;
    /**
     * 信息级别
     */
    private short msgLevel = 9;
    /**
     * 业务标识，是数字、字母和符号的组合
     */
    private String serviceId;
    /**
     * 计费用户类型字段：
     * 0：对目的终端MSISDN 计费； 1：对源终端MSISDN 计费； 2：对SP 计费； 3：表示本字段无效，对谁计费参见 Fee_terminal_Id 字段。
     */
    private short feeUserType = 2;
    /**
     * 被计费用户的号码，
     * 当Fee_UserType为 3 时该值有效，当Fee_UserType为 0、1、2 时该值无意义
     */
    private String feeTerminalId;
    /**
     * 被计费用户的号码类型，0：真实号码；1： 伪码
     */
    private short feeTerminalType = 0;
    /**
     * 0是普通GSM 类型，点到点方式 ,127 :写sim卡
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
     * 信息内容来源(SP_Id)
     */
    private String msgSrc;
    /**
     * 资费类别： 01：对“计费用户号码”免费； 02：对“计费用户号码”按条计信息费； 03：对“计费用户号码”按包月收取信息费。
     */
    private String feeType = "01";
    /**
     * 资费（以分为单位）
     */
    private String feeCode = "000000";
    /**
     * 存活有效期，格式遵循SMPP3.3协议
     */
    private String valIdTime;
    /**
     * 定时发送时间，格式遵循SMPP3.3协议
     */
    private String atTime;
    /**
     * 源号码。
     * SP的服务代码或前缀为服务代码的长号码,
     * 网关将该号码完整的填到 SMPP 协议 Submit_SM 消息相应的 source_addr 字段，该号码最终在用户手机上显示为短消息的主叫号码。
     */
    private String srcId;
    /**
     * 接收信息的用户数量(小于100 个用户)。
     */
    private short destUsrTl;
    /**
     * 接收短信的MSISDN号码
     */
    private String[] destTerminalId;
    /**
     * 接收短信的用户的号码类型，0：真实号 码；1：伪码
     */
    private short destTerminalType = 0;
    /**
     * 信息长度(Msg_Fmt值为0 时：<160 个字 节；其它<=140 个字节)，取值大于或等于 0
     */
    private short msgLength = 140;

    /**
     * 信息内容
     */
    private String msgContent;
    /**
     * 信息内容
     */
    private byte[] msgContents;
    /**
     * 点播业务使用的LinkID，非点播类业务的 MT 流程不使用该字段
     */
    private String linkId;

    public void setMsgContent(String msgContent) {

    }



}
