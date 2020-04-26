package org.okboom.wukong.dubbo.configuration;

import org.okboom.wukong.dubbo.proxy.DubboProxyService;
import org.okboom.wukong.dubbo.proxy.ReferenceCache;
import org.okboom.wukong.dubbo.proxy.ReferenceMemoryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tookbra
 */
@Configuration
public class DubboConfiguration {

    @Bean
    ReferenceCache referenceCache() {
        return new ReferenceMemoryCache();
    }

    @Bean
    DubboProxyService dubboProxyService(ReferenceCache referenceCache) {
        return new DubboProxyService(referenceCache);
    }
}
