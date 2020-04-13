package org.okboom.wukong.sms.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.sms.connect.AbstractClient;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;

/**
 * @author tookbra
 */
@Slf4j
public abstract class AbstractSessionManager extends ChannelDuplexHandler {

    /**
     * 连接状态
     **/
    protected SessionState state = SessionState.DisConnect;

    protected AbstractConnectBean abstractConnectBean;

    public AbstractSessionManager(AbstractConnectBean abstractConnectBean) {
        this.abstractConnectBean = abstractConnectBean;
    }

    /**
     * 建立连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (state == SessionState.DisConnect) {
            doLogin(ctx.channel());
        }
        super.channelActive(ctx);
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 第一次收到报文是发送登录回复的报文， 需在次验证服务端回复的状态
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(state == SessionState.DisConnect) {
            receiveConnectMessage(ctx, msg);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(state == SessionState.DisConnect){
            ctx.close();
        }else{
            super.exceptionCaught(ctx, cause);
        }
    }

    /**
     * 验证登录回复报文
     * @param ctx
     * @param msg
     */
    private void receiveConnectMessage(ChannelHandlerContext ctx, Object msg) {
        int status = validLoginMsg(msg);
        if(status == 0) {
            AbstractClient abstractClient = abstractConnectBean.getConnector();
            if(abstractClient.addChannel(ctx.channel())) {
                state = SessionState.Connect;
                log.info("{} login success on channel {}", ctx.channel());
            }
        } else {
            log.info("{} login failed (status = {}) on channel {}", status ,ctx.channel());
            ctx.close();
        }
    }

    /**
     * 发送握手消息
     * @param channel
     */
    protected abstract void doLogin(Channel channel);

    /**
     * 验证登录信息
     * @param msg
     * @return
     */
    protected abstract int validLoginMsg(Object msg);
}
