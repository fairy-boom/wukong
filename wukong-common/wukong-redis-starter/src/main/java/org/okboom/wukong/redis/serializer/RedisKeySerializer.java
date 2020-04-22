package org.okboom.wukong.redis.serializer;

import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author tookbra
 */
public class RedisKeySerializer implements RedisSerializer<Object> {
    private final Charset charset;
    private final ConversionService converter;

    public RedisKeySerializer() {
        this(StandardCharsets.UTF_8);
    }

    public RedisKeySerializer(Charset charset) {
        Objects.requireNonNull(charset, "Charset must not be null");
        this.charset = charset;
        this.converter = DefaultConversionService.getSharedInstance();
    }


    @Override
    public byte[] serialize(Object o) throws SerializationException {
        Objects.requireNonNull(o, "redis key is null");
        String key;
        if (o instanceof SimpleKey) {
            key = "";
        } else if (o instanceof String) {
            key = (String) o;
        } else {
            key = converter.convert(o, String.class);
        }
        return key.getBytes(this.charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        // redis keys 会用到反序列化
        if (bytes == null) {
            return null;
        }
        return new String(bytes, charset);
    }
}
