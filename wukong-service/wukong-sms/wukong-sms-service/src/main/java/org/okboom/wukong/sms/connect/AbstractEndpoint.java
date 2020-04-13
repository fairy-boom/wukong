package org.okboom.wukong.sms.connect;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.sms.common.Constants;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;
import org.okboom.wukong.sms.exchange.protocol.Message;
import org.okboom.wukong.sms.handler.BlackHoleHandler;
import org.okboom.wukong.sms.handler.SubmitSmsHandler;
import org.okboom.wukong.sms.session.SessionState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tookbra
 */
@Slf4j
public abstract class AbstractEndpoint implements Client {

    private AbstractConnectBean abstractConnectBean;

    private ConcurrentHashMap<String, List<Channel>> channelMap = new ConcurrentHashMap<>(16);

    public AbstractEndpoint(AbstractConnectBean abstractConnectBean) {
        this.abstractConnectBean = abstractConnectBean;
    }

    @Override
    public AbstractConnectBean getConnectBean() {
        return abstractConnectBean;
    }

    @Override
    public boolean addChannel(Channel channel) {
//        if(abstractConnectBean.getMaxConnect() < abstractConnectBean.getMaxConnect()) {
            channel.attr(Constants.ATTRIBUTE_KEY).set(SessionState.Connect);

            List<Channel> channels = channelMap.get("");
            if(channels == null) {
                channels = new ArrayList<>();
            }
            channels.add(channel);
            channelMap.put("", channels);

            // 增加流量整形 ，每个连接每秒发送，接收消息数不超过配置的值
            channel.pipeline().addAfter("codecName", "ChannelTrafficAfter",
                    new MessageChannelTrafficShapingHandler(abstractConnectBean.getWriteLimit(), abstractConnectBean.getReadLimit(), 250));
            bindHandler(channel.pipeline());
            return true;
//        } else {
//            log.warn("allowed max channel count: {} ,deny to login.{}", abstractConnectBean);
//            return false;
//        }
    }

    /**
     * 消息流量整形
     */
    private class MessageChannelTrafficShapingHandler extends ChannelTrafficShapingHandler {
        public MessageChannelTrafficShapingHandler(long writeLimit, long readLimit, long checkInterval) {
            super(writeLimit, readLimit, checkInterval);
        }

        @Override
        protected long calculateSize(Object msg) {
            if (msg instanceof ByteBuf) {
                return ((ByteBuf) msg).readableBytes();
            }
            if (msg instanceof ByteBufHolder) {
                return ((ByteBufHolder) msg).content().readableBytes();
            }
            return doCalculateSize(msg);
        }

        protected long doCalculateSize(Object msg){
            if(msg instanceof Message){
                return 1L;
            }else{
                return -1L;
            }
        }
    }

    /**
     * 连接建立成功后要加载的channelHandler
     */
    protected void bindHandler(ChannelPipeline pipe) {
//        pipe.addFirst("socketLog", new LoggingHandler(String.format(GlobalConstance.loggerNamePrefix, entity.getId()), LogLevel.TRACE));
        // 调用子类的bind方法
        doBindHandler(pipe);
        pipe.addLast(new SubmitSmsHandler());
        // 黑洞处理，丢弃所有消息
        pipe.addLast("BlackHandler", new BlackHoleHandler());
    }

    /**
     * 绑定业务处理
     * @param pipeline
     */
    protected abstract void doBindHandler(ChannelPipeline pipeline);
}
