package org.okboom.wukong.gateway.configuration;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.okboom.wukong.dubbo.proxy.DubboProxyService;
import org.okboom.wukong.dubbo.proxy.ReferenceCache;
import org.okboom.wukong.gateway.filter.AuthFilter;
import org.okboom.wukong.gateway.filter.InvokeFilter;
import org.okboom.wukong.gateway.filter.RequestFilter;
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
    public GatewayRouteServiceListener gatewayRouteServiceListener(NacosDiscoveryProperties nacosDiscoveryProperties,
                                                                   NacosConfigProperties nacosConfigProperties,
                                                                   GatewayRouteService gatewayRouteService,
                                                                   ConfigurableEnvironment environment,
                                                                   NacosConfigManager nacosConfigManager,
                                                                   ReferenceCache referenceCache) {
        return new GatewayRouteServiceListener(nacosDiscoveryProperties, nacosConfigProperties, gatewayRouteService,
                environment, nacosConfigManager, dynamicRouteConfigName, referenceCache);
    }

    @Bean
    public GatewayRouteService gatewayRouteService(RouteDefinitionWriter routeDefinitionWriter, ReferenceCache referenceCache) {
        return new GatewayRouteService(routeDefinitionWriter, referenceCache);
    }

    @Bean
    @Order(-1000)
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    public InvokeFilter invokeFilter(DubboProxyService dubboProxyService) {
        return new InvokeFilter(dubboProxyService);
    }
}
