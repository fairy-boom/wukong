package org.okboom.wukong.gateway.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 权限过滤
 * @author tookbra
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
        return chain.filter(exchange);

//        String token = serverHttpRequest.getHeaders().getFirst("token");
//        if(StrUtil.isBlank(token)) {
//            return null;
//        }
//        return null;
    }
}
