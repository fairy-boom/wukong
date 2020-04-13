package org.okboom.wukong.sms.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.SocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.sms.common.Constants;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;

import java.util.concurrent.TimeUnit;

/**
 * @author tookbra
 */
@Slf4j
public abstract class AbstractClient extends AbstractEndpoint implements Client {

    private final Bootstrap bootstrap = new Bootstrap();

    private static final EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("workGroup", true));

    public AbstractClient(AbstractConnectBean abstractConnectBean) {
        super(abstractConnectBean);
    }

    @Override
    public void doOpen() {
        AbstractConnectBean abstractConnectBean = getConnectBean();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_RCVBUF, 2048)
                .option(ChannelOption.SO_SNDBUF, 2048)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(1024))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.max(3000, abstractConnectBean.getConnectTimeout()))
                .handler(initPipeLine());
        doConnect(abstractConnectBean.getHost(), abstractConnectBean.getPort(), abstractConnectBean.getRetryConnect(), 0);
    }

    /**
     * 连接
     * @param host
     * @param port
     * @return
     */
    private ChannelFuture doConnect(String host, Integer port, Integer retry, Integer retried) {
        ChannelFuture future = bootstrap.connect(SocketUtils.socketAddress(host,port));
        future.addListener(f -> {
            if(f.isSuccess()) {
                log.info("retry connect to next host {}:{}", host, port);
            } else {
                if(retried < retry) {
                    doConnect(host, port, retry, retried + 1);
                } else {
                    log.error("Connect to {}:{} failed, retried:{}. cause by {}.", host, port, retried, f.cause().getMessage());
                }
            }
        });
        return future;
    }

    @Override
    public void doClose() {

    }

    @Override
    public void doClose(Channel channel) {
        try {
            if(channel.isOpen()) {
                channel.close().sync();
            }
        } catch (InterruptedException e) {
            log.error("close channel error: {}", e.getMessage());
        }
    }

    /**
     * 初始化通道
     * @return
     */
    protected ChannelInitializer<?> initPipeLine() {
        return new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(Constants.IDLE_STATE_HANDLER_NAME, new IdleStateHandler(0, 0,
                        getConnectBean().getHeartbeatInterval(Constants.DEFAULT_HEARTBEAT), TimeUnit.SECONDS));
                doInitPipeLine(pipeline);
            }
        };
    };

    /**
     * 初始化通道
     * @param pipeline
     */
    protected abstract void doInitPipeLine(ChannelPipeline pipeline);
}
