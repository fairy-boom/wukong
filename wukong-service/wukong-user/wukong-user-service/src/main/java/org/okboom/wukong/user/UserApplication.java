package org.okboom.wukong.user;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tookbra
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
