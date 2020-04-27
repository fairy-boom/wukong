package org.okboom.wukong.dubbo.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.okboom.wukong.dubbo.domain.Metadata;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author tookbra
 */
@Slf4j
public final class ReferenceMemoryCache implements ReferenceCache {

    private final int maxCount = 5000;

    private final LoadingCache<String, ReferenceConfig<GenericService>> cache = CacheBuilder.newBuilder()
            .maximumWeight(maxCount)
            .weigher((Weigher<String, ReferenceConfig<GenericService>>) (string, ReferenceConfig) -> getSize())
            .removalListener(notification -> {
                ReferenceConfig config = notification.getValue();
                if (config != null) {
                    try {
                        Class cz = config.getClass();
                        Field field = cz.getDeclaredField("ref");
                        field.setAccessible(true);
                        field.set(config, null);
                    } catch (Exception e) {
                        log.error("修改ref为null", e);
                    }
                }
            })
            .build(new CacheLoader<String, ReferenceConfig<GenericService>>() {
                @Override
                public ReferenceConfig<GenericService> load(final String key) {
                    return new ReferenceConfig<>();
                }
            });

    /**
     * 创建 ReferenceConfig
     * @param metadata
     */
    public ReferenceConfig<GenericService> build(Metadata metadata) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setGeneric(CommonConstants.GENERIC_SERIALIZATION_DEFAULT);
        Optional.ofNullable(metadata.getServiceName()).ifPresent(reference::setInterface);
        Optional.ofNullable(metadata.getVersion()).ifPresent(reference::setVersion);
        Optional.ofNullable(metadata.getGroup()).ifPresent(reference::setGroup);
        Optional.ofNullable(metadata.getLoadBalance()).ifPresent(reference::setLoadbalance);
        Optional.ofNullable(metadata.getTimeout()).ifPresent(reference::setTimeout);
        Optional.ofNullable(metadata.getRetries()).ifPresent(reference::setRetries);

        Object obj = reference.get();
        if (obj != null) {
            cache.put(metadata.getServiceName(), reference);
        }
        return reference;
    }

    @Override
    public int getSize() {
        return (int)cache.size();
    }

    @Override
    public <T> ReferenceConfig<T> get(String key) {
        try {
            return (ReferenceConfig<T>)cache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public void remove(String key) {
        cache.invalidate(key);
    }

    @Override
    public void removeAll() {
        cache.invalidateAll();
    }

    @Override
    public void refresh(List<Metadata> metadata) {
        if(CollectionUtil.isNotEmpty(metadata)) {
            this.removeAll();
            metadata.forEach(this::build);
        }
    }
}
