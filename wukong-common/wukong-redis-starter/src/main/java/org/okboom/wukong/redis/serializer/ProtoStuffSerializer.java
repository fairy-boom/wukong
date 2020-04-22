package org.okboom.wukong.redis.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

/**
 *  ProtoStuff 序列化
 * @author tookbra
 */
public class ProtoStuffSerializer implements RedisSerializer<Object> {
    private static final Schema<BytesWrapper> SCHEMA = RuntimeSchema.getSchema(BytesWrapper.class);

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return null;
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(new BytesWrapper<>(o), SCHEMA, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes)) {
            return null;
        }
        BytesWrapper<Object> wrapper = new BytesWrapper<>();
        ProtostuffIOUtil.mergeFrom(bytes, wrapper, SCHEMA);
        return wrapper.getValue();
    }
}
