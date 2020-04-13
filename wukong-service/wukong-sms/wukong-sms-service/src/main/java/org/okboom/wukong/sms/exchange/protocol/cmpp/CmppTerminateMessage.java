package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppTerminateMessage extends Message {

    public CmppTerminateMessage() {
        super(CmppCommand.TERMINATE, 12);
    }
}
