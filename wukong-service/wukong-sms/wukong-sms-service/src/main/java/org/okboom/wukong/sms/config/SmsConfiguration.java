package org.okboom.wukong.sms.config;

import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.sms.connect.ConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tookbra
 */
@Configuration
public class SmsConfiguration {

    @Bean
    public ConnectionManager connectionManager(Sequence sequence) {
        return new ConnectionManager(sequence);
    }
}
