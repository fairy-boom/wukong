package org.okboom.wukong.gateway.route;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 路由服务
 * @author tookbra
 */
@Slf4j
public class GatewayRouteService implements ApplicationEventPublisherAware {

    private final RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    public GatewayRouteService(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 更新路由
     */
    public void update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
           log.error("gateway route update error: {}", e.getMessage());
        }
    }


    /**
     * 更新路由
     */
    public void updateList(List<RouteDefinition> routeDefinitions) {
        routeDefinitions.forEach(this::update);
    }
}
