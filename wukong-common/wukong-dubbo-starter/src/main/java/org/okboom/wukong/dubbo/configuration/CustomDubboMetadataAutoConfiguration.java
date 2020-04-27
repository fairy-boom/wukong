package org.okboom.wukong.dubbo.configuration;

import lombok.AllArgsConstructor;
import org.apache.dubbo.config.spring.ServiceBean;
import org.apache.dubbo.config.spring.context.event.ServiceBeanExportedEvent;
import org.okboom.wukong.dubbo.annotation.Provider;
import org.okboom.wukong.dubbo.cache.ApplicationCache;
import org.okboom.wukong.dubbo.domain.Metadata;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tookbra
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class CustomDubboMetadataAutoConfiguration {

    public final ConfigurableEnvironment environment;
    public final ApplicationCache applicationCache;

    @EventListener(ServiceBeanExportedEvent.class)
    public void onServiceBeanExported(ServiceBeanExportedEvent event) {
        ServiceBean serviceBean = event.getServiceBean();
        publishServiceMetadata(serviceBean);
    }

    private void publishServiceMetadata(ServiceBean serviceBean) {
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
