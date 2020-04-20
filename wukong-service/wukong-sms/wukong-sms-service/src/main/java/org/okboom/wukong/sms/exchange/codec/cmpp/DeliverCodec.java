package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.okboom.wukong.sms.common.ByteBufUtil;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppDeliverMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppDeliverRespMessage;

import java.util.List;

/**
 * @author tookbra
 */
public class DeliverCodec extends MessageToMessageCodec<Packet, CmppDeliverRespMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppDeliverRespMessage msg, List<Object> out) throws Exception {
        ByteBuf bodyBuffer = ctx.alloc().buffer(msg.getBodyLength());
        bodyBuffer.writeLong(msg.getMsgId());
        bodyBuffer.writeInt(msg.getResult());

        msg.setBody(ByteBufUtil.toArray(bodyBuffer, bodyBuffer.readableBytes()));
        ReferenceCountUtil.release(bodyBuffer);
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppDeliverMessage respMessage = new CmppDeliverMessage();

        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBody());

        respMessage.setMsgId(bodyBuffer.readLong());
        respMessage.setDestId(bodyBuffer.readCharSequence(21, CharsetUtil.UTF_8).toString().trim());
        respMessage.setServiceId(bodyBuffer.readCharSequence(10, CharsetUtil.UTF_8).toString().trim());
        respMessage.setTpPid(bodyBuffer.readUnsignedByte());
        respMessage.setTpUdHi(bodyBuffer.readUnsignedByte());
        respMessage.setMsgFmt(bodyBuffer.readUnsignedByte());
        respMessage.setSrcTerminalId(bodyBuffer.readCharSequence(32, CharsetUtil.UTF_8).toString().trim());
        respMessage.setSrcTerminalType(bodyBuffer.readUnsignedByte());

        short registeredDelivery = bodyBuffer.readUnsignedByte();
        int frameLength = (short)(bodyBuffer.readUnsignedByte() & 0xffff);

        if(registeredDelivery == 0) {
            // 非状态报告
            byte[] content = new byte[frameLength];
            bodyBuffer.readBytes(content);
            respMessage.setMsgContent(content);
            respMessage.setMsgLength((short)frameLength);
        } else {
            // 状态报告
        }
        out.add(respMessage);

        ReferenceCountUtil.release(bodyBuffer);
    }
}
