//package org.okboom.wukong.dubbo.configuration;
//
//import org.okboom.wukong.dubbo.cache.ApplicationCache;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.ConfigurableEnvironment;
//
///**
// * @author tookbra
// */
//@Configuration
//public class DubboClientConfiguration {
//
//    @Bean
//    ApplicationCache applicationCache() {
//        return new ApplicationCache();
//    }
//
//    @Bean
//    DubboServiceBeanPostProcessor dubboServiceBeanPostProcessor(ConfigurableEnvironment environment,
//                                                                ApplicationCache applicationCache) {
//        return new DubboServiceBeanPostProcessor(environment, applicationCache);
//    }
//}
