package org.okboom.wukong.sms.exchange.codec.cmpp;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageCodec;
import org.okboom.wukong.sms.exchange.protocol.Command;
import org.okboom.wukong.sms.exchange.protocol.Packet;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppCommand;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 编解码适配
 * @author tookbra
 */
@ChannelHandler.Sharable
public class CmppCodecAdapter extends ChannelDuplexHandler {

    private ConcurrentHashMap<Integer, MessageToMessageCodec> codecMap = new ConcurrentHashMap<>(16);

    public CmppCodecAdapter() {
        for (Command command : CmppCommand.values()) {
            codecMap.put(command.getCommandId(), command.getCodec());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer commandId = ((Packet)msg).getCommandId();
        MessageToMessageCodec messageToMessageCodec = codecMap.get(commandId);
        if(messageToMessageCodec != null) {
            messageToMessageCodec.channelRead(ctx, msg);
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Integer commandId = ((Packet)msg).getCommandId();
        MessageToMessageCodec messageToMessageCodec = codecMap.get(commandId);
        if(messageToMessageCodec != null) {
            messageToMessageCodec.write(ctx, msg, promise);
        }
    }
}
