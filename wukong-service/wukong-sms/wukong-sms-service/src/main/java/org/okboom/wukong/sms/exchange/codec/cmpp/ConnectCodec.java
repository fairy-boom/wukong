package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import org.okboom.wukong.sms.common.ByteBufUtil;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppConnectMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppConnectRespMessage;

import java.util.List;

/**
 * @author tookbra
 */
public class ConnectCodec extends MessageToMessageCodec<Packet, CmppConnectMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppConnectMessage msg, List<Object> out) throws Exception {
        ByteBuf bodyBuffer =  ctx.alloc().buffer(msg.getBodyLength());

        bodyBuffer.writeBytes(msg.getSourceAddr().getBytes());
        bodyBuffer.writeBytes(msg.getAuthenticatorSource());
        bodyBuffer.writeByte(msg.getVersion());
        bodyBuffer.writeInt(msg.getTimestamp());

        msg.setBody(ByteBufUtil.toArray(bodyBuffer, bodyBuffer.readableBytes()));
        ReferenceCountUtil.release(bodyBuffer);
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppConnectRespMessage respMessage = new CmppConnectRespMessage();
        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBody());
        respMessage.setStatus(bodyBuffer.readUnsignedInt());
        bodyBuffer.readBytes(16);
        respMessage.setVersion(bodyBuffer.readUnsignedByte());
        ReferenceCountUtil.release(bodyBuffer);
        out.add(respMessage);
    }
}
