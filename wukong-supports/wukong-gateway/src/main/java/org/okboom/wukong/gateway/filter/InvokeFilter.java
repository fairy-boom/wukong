package org.okboom.wukong.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.okboom.wukong.dubbo.proxy.DubboProxyService;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author tookbra
 */
@Slf4j
public class InvokeFilter implements GlobalFilter {

    private final DubboProxyService dubboProxyService;

    public InvokeFilter(DubboProxyService dubboProxyService) {
        this.dubboProxyService = dubboProxyService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
        dubboProxyService.genericInvoker(null);
        return chain.filter(exchange);
    }
}
