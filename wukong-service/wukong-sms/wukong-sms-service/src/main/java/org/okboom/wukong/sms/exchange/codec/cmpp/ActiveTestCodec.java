package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import org.okboom.wukong.sms.common.Constants;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppActiveTestMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppActiveTestRespMessage;

import java.util.List;

/**
 * @author tookbra
 */
public class ActiveTestCodec extends MessageToMessageCodec<Packet, CmppActiveTestMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CmppActiveTestMessage msg, List<Object> out) throws Exception {
        msg.setBody(Constants.EMPTY_BYTES);
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppActiveTestRespMessage respMessage = new CmppActiveTestRespMessage();
        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBody());

        if(bodyBuffer.readableBytes() > 0) {
            respMessage.setReserved(bodyBuffer.readByte());
        }

        ReferenceCountUtil.release(bodyBuffer);
        out.add(respMessage);
    }
}
