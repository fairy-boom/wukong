package org.okboom.wukong.sms.exchange.protocol.cmpp;

import io.netty.handler.codec.MessageToMessageCodec;
import org.okboom.wukong.sms.exchange.codec.cmpp.*;
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
     * 提交短信
     */
    SUBMIT(0x00000004, 0, TerminateCodec.class),
    /**
     * 提交短信应答
     */
    SUBMIT_RESP(0x80000004, 0, TerminateCodec.class),
    /**
     * 发送短信状态查询
     */
    QUERY(0x00000006, 27, QueryCodec.class),
    /**
     * 发送短信状态查询应答
     */
    QUERY_RESP(0x80000006, 51, QueryCodec.class),
    /**
     * 删除短信
     */
    CANCEL(0x00000007, 8, CancelCodec.class),
    /**
     * 删除短信 应答
     */
    CANCEL_RESP(0x80000007, 4, CancelCodec.class),
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
