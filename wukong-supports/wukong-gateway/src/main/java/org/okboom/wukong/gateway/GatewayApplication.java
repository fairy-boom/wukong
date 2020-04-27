package org.okboom.wukong.gateway;

import com.alibaba.cloud.dubbo.metadata.repository.DubboServiceMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tookbra
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {

    @Autowired
    RouteDefinitionRepository routeDefinitionRepository;

    @Autowired
    DubboServiceMetadataRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        routeDefinitionRepository.getRouteDefinitions().toStream().forEach(t -> {
            Map<String, Object> metadata = t.getMetadata();
            if(metadata != null) {
                String serviceName = (String)metadata.get("serviceName");
                repository.initializeMetadata(serviceName);
            }
        });
    }
}
