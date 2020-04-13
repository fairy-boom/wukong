package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.okboom.wukong.sms.common.ByteBufUtil;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppQueryMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppQueryRespMessage;

import java.util.List;

/**
 * @author tookbra
 */
public class QueryCodec extends MessageToMessageCodec<Packet, CmppQueryMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppQueryMessage msg, List<Object> out) throws Exception {
        ByteBuf bodyBuffer =  ctx.alloc().buffer(msg.getBodyLength());
        bodyBuffer.writeBytes(msg.getTime().getBytes(CharsetUtil.UTF_8));
        bodyBuffer.writeShort(msg.getQueryType());
        bodyBuffer.writeBytes(msg.getQueryCode().getBytes(CharsetUtil.UTF_8));
        bodyBuffer.writeBytes(msg.getReserve().getBytes(CharsetUtil.UTF_8));

        msg.setBody(ByteBufUtil.toArray(bodyBuffer, bodyBuffer.readableBytes()));
        ReferenceCountUtil.release(bodyBuffer);
        out.add(msg);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppQueryRespMessage respMessage = new CmppQueryRespMessage();
        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBody());

        respMessage.setTime(bodyBuffer.readCharSequence(8, CharsetUtil.UTF_8).toString().trim());
        respMessage.setQueryType(bodyBuffer.readByte());
        respMessage.setQueryCode(bodyBuffer.readCharSequence(10, CharsetUtil.UTF_8).toString().trim());
        respMessage.setMtTlMsg(bodyBuffer.readUnsignedInt());
        respMessage.setMtTlUsr(bodyBuffer.readUnsignedInt());
        respMessage.setMtScs(bodyBuffer.readUnsignedInt());
        respMessage.setMtWt(bodyBuffer.readUnsignedInt());
        respMessage.setMtFl(bodyBuffer.readUnsignedInt());
        respMessage.setMoScs(bodyBuffer.readUnsignedInt());
        respMessage.setMoWt(bodyBuffer.readUnsignedInt());
        respMessage.setMoFl(bodyBuffer.readUnsignedInt());
        ReferenceCountUtil.release(bodyBuffer);
        out.add(respMessage);
    }
}
