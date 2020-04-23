package org.okboom.wukong.gateway.configuration;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.okboom.wukong.gateway.route.GatewayRouteService;
import org.okboom.wukong.gateway.route.GatewayRouteServiceListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author tookbra
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

    @Value("${spring.cloud.nacos.config.metadata.dynamicRouteName}")
    private String dynamicRouteConfigName;

    @Bean
    @Order()
    public GatewayRouteServiceListener gatewayRouteServiceListener(NacosDiscoveryProperties nacosDiscoveryProperties,
                                                                   NacosConfigProperties nacosConfigProperties,
                                                                   GatewayRouteService gatewayRouteService,
                                                                   ConfigurableEnvironment environment,
                                                                   NacosConfigManager nacosConfigManager) {
        return new GatewayRouteServiceListener(nacosDiscoveryProperties,
                nacosConfigProperties, gatewayRouteService, environment, nacosConfigManager, dynamicRouteConfigName);
    }

    @Bean
    public GatewayRouteService gatewayRouteService(RouteDefinitionWriter routeDefinitionWriter) {
        return new GatewayRouteService(routeDefinitionWriter);
    }
}
