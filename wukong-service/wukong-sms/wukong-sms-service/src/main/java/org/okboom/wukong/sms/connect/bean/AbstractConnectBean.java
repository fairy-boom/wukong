package org.okboom.wukong.sms.connect.bean;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.sms.connect.AbstractClient;

import java.io.Serializable;

/**
 * 短信网关连接对象
 * @author tookbra
 */
@Data
@ToString
public abstract class AbstractConnectBean implements Serializable {
    private static final long serialVersionUID = 8791043890819547157L;

    /**
     * 网关地址
     */
    private String host;

    /**
     * 网关端口
     */
    private Integer port;

    /**
     * sp号码
     */
    private String spNum;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginSecret;

    /**
     * 限流
     */
    private Integer writeLimit;

    private Integer readLimit;

    /**
     * 最大连接数
     */
    private Integer maxConnect;

    /**
     * 备注
     */
    private String description;

    /**
     * 连接超时时间
     */
    private Integer connectTimeout;

    /**
     * 心跳时间
     */
    private Integer heartbeatInterval;

    /**
     * 重试次数
     */
    private Integer retryConnect;


    public Integer getConnectTimeout() {
        if(connectTimeout == null) {
            return 0;
        }
        return connectTimeout;
    }

    public Integer getHeartbeatInterval(int defaultValue) {
        if(heartbeatInterval == null) {
            return defaultValue;
        }
        return heartbeatInterval;
    }

    /**
     * 获取连接器
     * @return
     */
    public AbstractClient getConnector() {
        return this.buildConnector();
    }

    /**
     * 创建连接器
     * @return
     */
    abstract protected AbstractClient buildConnector();
}
