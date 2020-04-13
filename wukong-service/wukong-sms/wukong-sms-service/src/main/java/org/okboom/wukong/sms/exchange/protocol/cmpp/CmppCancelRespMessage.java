package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppCancelRespMessage extends Message {

    public CmppCancelRespMessage() {
        super(CmppCommand.QUERY_RESP, 12);
    }

    /**
     * 成功标识。 0：成功; 1：失败
     */
    private Long successId;

}
