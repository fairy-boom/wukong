package org.okboom.wukong.sms.exchange.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.okboom.wukong.sms.exchange.protocol.Message;
import org.okboom.wukong.sms.exchange.protocol.Packet;

import java.util.List;

/**
 * @author tookbra
 */
public class HeaderCodec extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        Integer totalLength = msg.getTotalLength();
        ByteBuf buf = ctx.alloc().buffer(totalLength);
        buf.writeInt(totalLength);
        buf.writeInt(msg.getCommandId());
        buf.writeInt(msg.getSeqId());
        if(msg.getBodyLength() > 0) {
            buf.writeBytes(msg.getBody());
        }
        out.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        Packet packet = new Message();
        Integer totalLength = msg.readInt();
        packet.setTotalLength(totalLength);
        packet.setCommandId(msg.readInt());
        packet.setSeqId(msg.readInt());

        byte[] body = new byte[totalLength - 12];
        msg.readBytes(body);
        packet.setBody(body);

        out.add(packet);
    }
}
