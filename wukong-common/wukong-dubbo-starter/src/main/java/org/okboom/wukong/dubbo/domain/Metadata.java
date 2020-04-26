package org.okboom.wukong.dubbo.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
public class Metadata implements Serializable {
    private static final long serialVersionUID = 5455113597659899027L;

    /**
     * 接口名
     */
    private String serviceName;
    /**
     * 版本号
     */
    private String version;
    /**
     * 分组
     */
    private String group;
    /**
     * 负载均衡
     */
    private String loadBalance = "Rondom";
    /**
     * 超时时间
     */
    private Integer timeout;
    /**
     * 重试次数
     */
    private Integer retries;
}
