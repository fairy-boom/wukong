package org.okboom.wukong.sms.exchange.protocol.cmpp;

import io.netty.handler.codec.MessageToMessageCodec;
import org.okboom.wukong.sms.exchange.codec.cmpp.ActiveTestCodec;
import org.okboom.wukong.sms.exchange.codec.cmpp.ConnectCodec;
import org.okboom.wukong.sms.exchange.codec.cmpp.TerminateCodec;
import org.okboom.wukong.sms.exchange.protocol.Command;

/**
 * 指令枚举
 * @author tookbra
 */
public enum CmppCommand implements Command {

    /**
     * 请求连接
     */
    CONNECT(0x00000001, 27, ConnectCodec.class),
    /**
     * 请求连接应答
     */
    CONNECT_RESP(0x80000001, 21, ConnectCodec.class),
    /**
     * 终止连接
     */
    TERMINATE(0x00000002, 0, TerminateCodec.class),
    /**
     * 终止连接应答
     */
    TERMINATE_RESP(0x80000002, 0, TerminateCodec.class),
    /**
     * 终止连接
     */
    QUERY(0x00000006, 27, TerminateCodec.class),
    /**
     * 终止连接应答
     */
    QUERY_RESP(0x80000006, 51, TerminateCodec.class),
    /**
     * 激活测试
     */
    ACTIVE_TEST(0x00000008, 0, ActiveTestCodec.class),
    /**
     * 激活测试应答
     */
    ACTIVE_TEST_RESP(0x80000008, 1, ActiveTestCodec.class);


    private Integer commandId;
    private Integer bodyLength;
    private Class<? extends MessageToMessageCodec> codec;

    CmppCommand(Integer commandId, Integer bodyLength, Class<? extends MessageToMessageCodec> codec) {
        this.commandId = commandId;
        this.bodyLength = bodyLength;
        this.codec = codec;
    }

    @Override
    public Integer getCommandId() {
        return commandId;
    }

    @Override
    public Integer getBodyLength() {
        return bodyLength;
    }

    @Override
    public MessageToMessageCodec getCodec() {
        try {
            return codec.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
