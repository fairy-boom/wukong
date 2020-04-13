package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.okboom.wukong.sms.common.Constants;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppTerminateMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppTerminateRespMessage;

import java.util.List;

/**
 * @author tookbra
 */
public class TerminateCodec extends MessageToMessageCodec<Packet, CmppTerminateMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppTerminateMessage msg, List<Object> out) throws Exception {
        msg.setBody(Constants.EMPTY_BYTES);
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppTerminateRespMessage respMessage = new CmppTerminateRespMessage();
        out.add(respMessage);
    }
}
