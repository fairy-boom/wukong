package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppCancelMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppCancelRespMessage;

import java.util.List;

/**
 * @author tookbra
 */
public class CancelCodec extends MessageToMessageCodec<Packet, CmppCancelMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppCancelMessage msg, List<Object> out) throws Exception {
        msg.setBody(msg.getMsgId());
        out.add(msg);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppCancelRespMessage respMessage = new CmppCancelRespMessage();
        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBody());

        respMessage.setSuccessId(bodyBuffer.readUnsignedInt());
        ReferenceCountUtil.release(bodyBuffer);
        out.add(respMessage);
    }
}
