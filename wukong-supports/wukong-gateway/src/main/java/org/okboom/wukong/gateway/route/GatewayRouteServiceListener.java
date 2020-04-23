package org.okboom.wukong.gateway.route;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.common.tool.util.JSONUtil;
import org.okboom.wukong.gateway.constant.NacosConstant;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * 动态路由监听
 * @author tookbra
 */
@Slf4j
public class GatewayRouteServiceListener {

    private final NacosDiscoveryProperties nacosDiscoveryProperties;
    private final NacosConfigProperties nacosConfigProperties;
    private final GatewayRouteService gatewayRouteService;
    private final ConfigurableEnvironment environment;
    private final NacosConfigManager nacosConfigManager;
    /**
     * 动态路由配置名称
     */
    private final String dynamicRouteConfigName;

    public GatewayRouteServiceListener(NacosDiscoveryProperties nacosDiscoveryProperties,
                                       NacosConfigProperties nacosConfigProperties,
                                       GatewayRouteService gatewayRouteService,
                                       ConfigurableEnvironment environment,
                                       NacosConfigManager nacosConfigManager,
                                       String dynamicRouteConfigName) {
        this.nacosDiscoveryProperties = nacosDiscoveryProperties;
        this.nacosConfigProperties = nacosConfigProperties;
        this.gatewayRouteService = gatewayRouteService;
        this.environment = environment;
        this.nacosConfigManager = nacosConfigManager;
        this.dynamicRouteConfigName = dynamicRouteConfigName;
        this.dynamicRouteServiceListener();
    }

    /**
     * nacos配置中心监听
     */
    public void dynamicRouteServiceListener() {
        try {
            String dataId = NacosConstant.dataId(dynamicRouteConfigName, environment.getActiveProfiles()[0],
                    NacosConstant.NACOS_CONFIG_JSON_FORMAT);
            String group = nacosDiscoveryProperties.getGroup();
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    refreshRoute(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
            String configInfo = configService.getConfig(dataId, group, 5000);
            if (configInfo != null) {
                refreshRoute(configInfo);
            }
        } catch (Exception e) {
            log.error("update gateway route error: {}", e.getMessage());
        }
    }

    private void refreshRoute(String configInfo) {
        List<RouteDefinition> routeDefinitions = JSONUtil.parseList(configInfo, RouteDefinition.class);
        gatewayRouteService.updateList(routeDefinitions);
    }
}
