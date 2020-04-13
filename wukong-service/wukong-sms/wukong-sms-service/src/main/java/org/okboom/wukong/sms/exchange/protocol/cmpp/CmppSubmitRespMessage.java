package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppSubmitRespMessage extends Message {

    public CmppSubmitRespMessage() {
        super(CmppCommand.SUBMIT_RESP, 12);
    }


    /**
     * 信息标识
     */
    private Long msgId;
    /**
     * 0：正确；
     * 1：消息结构错；
     * 2：命令字错；
     * 3：消息序号重复；
     * 4：消息长度错；
     * 5：资费错；
     * 6：超过最大信息长；
     * 7：业务代码错；
     * 8：流量控制错；
     * 9：本网关不负责服务此计费号码；
     * 10：Src_Id错误；
     * 11：Msg_src错误；
     * 12：Fee_terminal_Id 错误；
     * 13：Dest_terminal_Id 错误；
     */
    private Long result;




}
