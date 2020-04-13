package org.okboom.wukong.sms.handler.cmpp;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import org.okboom.wukong.sms.common.SequenceUtil;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppActiveTestMessage;

/**
 * @author tookbra
 */
@ChannelHandler.Sharable
public class CmppIdleStateHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            CmppActiveTestMessage cmppActiveTestMessage = new CmppActiveTestMessage();
            cmppActiveTestMessage.setSeqId((int)SequenceUtil.nextId());
            ctx.channel().writeAndFlush(cmppActiveTestMessage);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
