package org.okboom.wukong.sms.connect;

import io.netty.channel.Channel;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;


/**
 * @author tookbra
 */
public interface Client<T extends AbstractConnectBean> {


    /**
     * 获取连接配置
     * @return
     */
    T getConnectBean();

    /**
     * 打开通道
     */
    void doOpen();

    /**
     * 关闭通道
     */
    void doClose();

    /**
     * 关闭通道
     * @param channel
     */
    void doClose(Channel channel);
}
