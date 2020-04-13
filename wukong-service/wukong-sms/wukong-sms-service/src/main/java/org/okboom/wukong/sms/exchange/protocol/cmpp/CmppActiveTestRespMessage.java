package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@NoArgsConstructor
public class CmppActiveTestRespMessage extends Message {

    private short reserved;
}
