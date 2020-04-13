package org.okboom.wukong.sms.connect.cmpp;

import io.netty.channel.Channel;
import org.okboom.wukong.sms.connect.AbstractCodecChannelInitializer;
import org.okboom.wukong.sms.exchange.codec.cmpp.CmppCodecAdapter;

/**
 * @author tookbra
 */
public class CmppCodecChannelInitializer extends AbstractCodecChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        ch.pipeline().addBefore(getPipelineBaseName(), "codecName", new CmppCodecAdapter());
    }
}
