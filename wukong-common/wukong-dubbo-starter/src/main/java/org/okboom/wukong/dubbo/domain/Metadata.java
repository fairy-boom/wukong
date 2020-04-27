package org.okboom.wukong.dubbo.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Metadata implements Serializable {
    private static final long serialVersionUID = 5455113597659899027L;

    /**
     * 应用名称
     * gateway route id对应
     */
    private String appName;
    /**
     * 接口名
     */
    private String serviceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 路径
     */
    private String path;
    /**
     * http method
     */
    private String httpMethodType;
    /**
     * 参数类型
     */
    private String parameterTypes;
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
