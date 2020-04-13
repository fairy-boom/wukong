package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.okboom.wukong.sms.common.ByteBufUtil;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppSubmitMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppSubmitRespMessage;

import java.util.Arrays;
import java.util.List;

/**
 * @author tookbra
 */
public class SubmitCodec extends MessageToMessageCodec<Packet, CmppSubmitMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CmppSubmitMessage msg, List<Object> out) throws Exception {
        ByteBuf bodyBuffer = ctx.alloc().buffer(151 + msg.getMsgLength() + (msg.getDestTerminalId().length - 1) * 32);

        bodyBuffer.writeBytes(msg.getMsgContents());
        bodyBuffer.writeByte(msg.getPkTotal());
        bodyBuffer.writeByte(msg.getPkNumber());
        bodyBuffer.writeByte(msg.getRegisteredDelivery());
        bodyBuffer.writeByte(msg.getMsgLevel());
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getServiceId(), 10));
        bodyBuffer.writeByte(msg.getFeeUserType());
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getFeeTerminalId(), 32));
        bodyBuffer.writeByte(msg.getFeeTerminalType());
        bodyBuffer.writeByte(msg.getTpPid());
        bodyBuffer.writeByte(msg.getTpUdHi());
        bodyBuffer.writeByte(msg.getMsgFmt());
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getMsgSrc(), 6));
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getFeeType(), 2));
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getFeeCode(), 6));
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getValIdTime(), 17));
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getAtTime(), 17));
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getSrcId(), 21));
        bodyBuffer.writeByte(msg.getDestUsrTl());
        Arrays.stream(msg.getDestTerminalId()).forEach(t -> {
            bodyBuffer.writeBytes(ByteBufUtil.str2bytes(t, 32));
        });
        bodyBuffer.writeByte(msg.getDestTerminalType());
        bodyBuffer.writeByte(msg.getMsgLength());
        bodyBuffer.writeBytes(new byte[0]);
        bodyBuffer.writeBytes(ByteBufUtil.str2bytes(msg.getLinkId(), 20));

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        CmppSubmitRespMessage respMessage = new CmppSubmitRespMessage();

        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBody());

        respMessage.setMsgId(bodyBuffer.readLong());
        respMessage.setResult(bodyBuffer.readUnsignedInt());
        ReferenceCountUtil.release(bodyBuffer);

        out.add(respMessage);
    }
}
