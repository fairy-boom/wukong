package org.okboom.wukong.redis.configure;

import org.okboom.wukong.redis.serializer.ProtoStuffSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author tookbra
 */
@Configuration
@AutoConfigureBefore(RedisTemplateConfigure.class)
public class SerializerConfiguration {

    @Bean
    @ConditionalOnMissingBean(RedisSerializer.class)
    public RedisSerializer<Object> redisSerializer(RedisProperties properties) {
        if (RedisProperties.SerializerType.PROTO_STUFF == properties.getSerializerType()) {
            return new ProtoStuffSerializer();
        }
        if (RedisProperties.SerializerType.JDK == properties.getSerializerType()) {
            return new JdkSerializationRedisSerializer();
        }
        return new GenericJackson2JsonRedisSerializer();
    }
}
