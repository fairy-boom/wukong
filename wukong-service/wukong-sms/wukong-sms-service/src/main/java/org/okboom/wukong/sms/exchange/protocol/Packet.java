package org.okboom.wukong.sms.exchange.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
public abstract class Packet implements Serializable {

    private static final long serialVersionUID = -4522871135919042043L;
    /**
     * 命令或响应类型
     */
    private Integer commandId;

    /**
     * 消息流水号
     */
    private Integer seqId;

    /**
     * 消息头长度
     */
    private Integer headerLength;

    /**
     * 消息体长度
     */
    private Integer bodyLength;

    /**
     * 消息长度
     */
    private Integer totalLength;

    /**
     * 包体
     */
    public byte[] body;
}
