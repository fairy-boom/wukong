package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppTerminateRespMessage extends Message {

    public CmppTerminateRespMessage() {
        super(CmppCommand.TERMINATE_RESP, 12);
    }
}
