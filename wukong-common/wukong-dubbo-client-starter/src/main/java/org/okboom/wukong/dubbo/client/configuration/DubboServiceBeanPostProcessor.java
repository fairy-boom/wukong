package org.okboom.wukong.dubbo.client.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.ServiceBean;
import org.okboom.wukong.dubbo.client.annotation.Provider;
import org.okboom.wukong.dubbo.client.cache.ApplicationCache;
import org.okboom.wukong.dubbo.domain.Metadata;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * dubbo 服务暴露后置处理
 * @author tookbra
 */
@Slf4j
public class DubboServiceBeanPostProcessor implements BeanPostProcessor {

    public final ConfigurableEnvironment environment;
    public final ApplicationCache applicationCache;

    public DubboServiceBeanPostProcessor(ConfigurableEnvironment environment, ApplicationCache applicationCache) {
        this.environment = environment;
        this.applicationCache = applicationCache;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ServiceBean) {
            handler((ServiceBean) bean);
        }
        return bean;
    }

    private void handler(final ServiceBean serviceBean) {
        Class<?> clazz = serviceBean.getRef().getClass();
        clazz = ClassUtils.getUserClass(clazz);
        final Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(clazz);
        for (Method method : methods) {
            Provider provider = method.getAnnotation(Provider.class);
            if (Objects.nonNull(provider)) {
                buildMeta(serviceBean, provider, method);
            }
        }
    }

    private void buildMeta(final ServiceBean serviceBean, final Provider provider, final Method method) {
        String appName = serviceBean.getApplication().getName();
        String path = provider.path();
        String serviceName = serviceBean.getInterface();
        String methodName = method.getName();
        Class<?>[] parameterTypesClazz = method.getParameterTypes();
        String parameterTypes = Arrays.stream(parameterTypesClazz).map(Class::getName)
                .collect(Collectors.joining(","));
        Metadata metadata = Metadata.builder()
                .appName(appName)
                .serviceName(serviceName)
                .methodName(methodName)
                .path(path)
                .httpMethodType(provider.httpMethod().name())
                .parameterTypes(parameterTypes)
                .build();
        applicationCache.updateMeta(metadata);
    }
}
