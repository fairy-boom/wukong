package org.okboom.wukong.gateway.constant;

/**
 * @author tookbra
 */
public interface NacosConstant {

    /**
     * json格式
     */
    String NACOS_CONFIG_JSON_FORMAT = "json";

    /**
     * `prefix` 默认为 `spring.application.name` 的值，也可以通过配置项 `spring.cloud.nacos.config.prefix`来配置。
     * `spring.profiles.active` 即为当前环境对应的 profile
     * @param prefix
     * @param profile
     * @param format
     * @return
     */
    static String dataId(String prefix, String profile, String format) {
        return prefix + "-" + profile + "." + format;
    }
}
