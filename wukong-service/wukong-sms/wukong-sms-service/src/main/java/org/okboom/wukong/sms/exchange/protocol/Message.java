package org.okboom.wukong.sms.exchange.protocol;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tookbra
 */
@Data
@NoArgsConstructor
public class Message extends Packet {

    private static final long serialVersionUID = -8846575751635608419L;

    public Message(Command command) {
        setCommandId(command.getCommandId());
        setBodyLength(command.getBodyLength());
    }

    public Message(Command command, Integer headerLength) {
        setCommandId(command.getCommandId());
        setBodyLength(command.getBodyLength());
        setHeaderLength(headerLength);
        setTotalLength(command.getBodyLength() + headerLength);
    }

    public Integer getPacketLength() {
        return 0;
    }
}
