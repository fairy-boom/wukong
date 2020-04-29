package org.okboom.wukong.dubbo.metadata.store;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.metadata.definition.ServiceDefinitionBuilder;
import org.apache.dubbo.metadata.definition.model.FullServiceDefinition;
import org.apache.dubbo.metadata.definition.model.MethodDefinition;
import org.apache.dubbo.metadata.definition.util.ClassUtils;
import org.apache.dubbo.metadata.report.identifier.*;
import org.apache.dubbo.metadata.report.support.AbstractMetadataReport;
import org.apache.dubbo.metadata.store.nacos.NacosMetadataReport;
import org.apache.dubbo.rpc.RpcException;
import org.okboom.wukong.dubbo.annotation.Provider;
import org.okboom.wukong.dubbo.metadata.definition.model.CustomFullServiceDefinition;
import org.okboom.wukong.dubbo.metadata.definition.model.CustomMethodDefinition;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 自定义元数据上报
 * @author tookbra
 * @date 2020/4/28
 * @description
 */
@Slf4j
public class CustomMetadataReport extends NacosMetadataReport {

    public CustomMetadataReport(URL url) {
        super(url);
    }

    @Override
    protected void doStoreProviderMetadata(MetadataIdentifier providerMetadataIdentifier, String serviceDefinitions) {
        String interfaceName = providerMetadataIdentifier.getServiceInterface();
        if(StringUtils.isNotEmpty(interfaceName)) {
            try {
                Class interfaceClass = Class.forName(interfaceName);
                List<Method> methods = ClassUtils.getPublicNonStaticMethods(interfaceClass);

                Gson gson = new Gson();
                FullServiceDefinition fullServiceDefinition = gson.fromJson(serviceDefinitions, FullServiceDefinition.class);
                CustomFullServiceDefinition customFullServiceDefinition = new CustomFullServiceDefinition();
                customFullServiceDefinition.setParameters(fullServiceDefinition.getParameters());
                customFullServiceDefinition.setCanonicalName(fullServiceDefinition.getCanonicalName());
                customFullServiceDefinition.setCodeSource(fullServiceDefinition.getCodeSource());
                customFullServiceDefinition.setTypes(fullServiceDefinition.getTypes());
                Map<String, MethodDefinition> map = fullServiceDefinition
                        .getMethods().stream().collect(Collectors.toMap(MethodDefinition::getName, t -> t));

                List<CustomMethodDefinition> customMethodDefinitions = new ArrayList<>();
                for (Method method : methods) {
                    MethodDefinition methodDefinition = map.get(method.getName());
                    if(Objects.isNull(methodDefinition)) {
                        continue;
                    }
                    CustomMethodDefinition customMethodDefinition = new CustomMethodDefinition();
                    customMethodDefinition.setName(methodDefinition.getName());
                    customMethodDefinition.setParameters(methodDefinition.getParameters());
                    customMethodDefinition.setParameterTypes(methodDefinition.getParameterTypes());
                    customMethodDefinition.setReturnType(methodDefinition.getReturnType());
                    Provider provider = method.getAnnotation(Provider.class);
                    if (Objects.nonNull(provider)) {
                        customMethodDefinition.setPath(provider.path());
                    }
                    customMethodDefinitions.add(customMethodDefinition);
                }
                customFullServiceDefinition.setMethods(customMethodDefinitions);
                serviceDefinitions = gson.toJson(customFullServiceDefinition);
                super.doStoreProviderMetadata(providerMetadataIdentifier, serviceDefinitions);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        log.error("publishProvider interfaceName is empty . interfaceName: {}" + providerMetadataIdentifier.getIdentifierKey());
    }
}
