package org.okboom.wukong.sms.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.sms.exchange.protocol.Packet;

/**
 * @author tookbra
 */
@Slf4j
@ChannelHandler.Sharable
public class SubmitSmsHandler extends ChannelDuplexHandler {

    public void sendSms(Packet packet) {

    }
}
