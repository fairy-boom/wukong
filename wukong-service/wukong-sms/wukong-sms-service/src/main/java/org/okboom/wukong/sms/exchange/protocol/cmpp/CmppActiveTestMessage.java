package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppActiveTestMessage extends Message {

    public CmppActiveTestMessage() {
        super(CmppCommand.ACTIVE_TEST, 12);
    }
}
