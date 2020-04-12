package org.okboom.wukong.sms;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.okboom.wukong.sms.connect.ConnectionManager;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;
import org.okboom.wukong.sms.connect.bean.CmppConnectBean;
import org.okboom.wukong.sms.domain.ChannelInfo;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tookbra
 */
public class SmsApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        // 获取短信配置

        List<ChannelInfo> channelInfos = new ArrayList<>();

        channelInfos.forEach(t -> {
            CmppConnectBean connectBean = new CmppConnectBean();

            ConnectionManager.INSTANCE.addConnect(connectBean);
        });

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup(0)).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_RCVBUF, 2048)
                .option(ChannelOption.SO_SNDBUF, 2048)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(1024))
                .handler(initPipeLine());
    }

    private ChannelHandler initPipeLine() {
        return new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {

            }
        };
    }
}
