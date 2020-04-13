package org.okboom.wukong.sms.exchange.protocol;

import io.netty.handler.codec.MessageToMessageCodec;

/**
 * @author tookbra
 */
public interface Command {

    /**
     * 获取指令
     * @return
     */
    Integer getCommandId();

    /**
     * 获取消息体长度
     * @return
     */
    Integer getBodyLength();

    /**
     * 获取对应的编解码器
     * @return
     */
    MessageToMessageCodec getCodec();
}
