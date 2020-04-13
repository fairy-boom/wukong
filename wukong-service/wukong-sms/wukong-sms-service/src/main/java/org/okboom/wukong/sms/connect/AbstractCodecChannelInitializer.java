package org.okboom.wukong.sms.connect;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.okboom.wukong.sms.exchange.codec.*;

/**
 * @author tookbra
 */
public abstract class AbstractCodecChannelInitializer extends ChannelInitializer<Channel> {


    public String getPipelineBaseName() {
        return "Codec";
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addBefore(getPipelineBaseName(), "FrameDecoder",
                        new LengthFieldBasedFrameDecoder(4 * 1024, 0, 4, -4, 0, true))
                .addBefore(getPipelineBaseName(), "HeaderCodec", new HeaderCodec());
    }
}
