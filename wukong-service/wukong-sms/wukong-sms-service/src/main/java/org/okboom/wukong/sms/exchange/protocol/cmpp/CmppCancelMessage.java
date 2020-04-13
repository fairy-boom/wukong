package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppCancelMessage extends Message {

    public CmppCancelMessage() {
        super(CmppCommand.CANCEL, 12);
    }

    /**
     * 信息标识（SP想要删除的信息标识）
     */
    private byte [] msgId;

}
