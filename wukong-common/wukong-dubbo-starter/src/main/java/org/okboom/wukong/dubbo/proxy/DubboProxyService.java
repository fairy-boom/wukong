package org.okboom.wukong.dubbo.proxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.okboom.wukong.dubbo.cache.ReferenceCache;
import org.okboom.wukong.dubbo.domain.Metadata;

import java.util.concurrent.CompletableFuture;

/**
 * @author tookbra
 */
@Slf4j
public final class DubboProxyService {

    private final ReferenceCache referenceCache;

    public DubboProxyService(ReferenceCache referenceCache) {
        this.referenceCache = referenceCache;
    }

    public void genericInvoker(final Metadata metadata) {
        ReferenceConfig<GenericService> reference = referenceCache.get(metadata.getServiceName());
        GenericService genericService = reference.get();
        

        Pair<String[], Object[]> pair;
        try {
            pair = new ImmutablePair<>(new String[]{}, new Object[]{});
            CompletableFuture<Object> future = genericService.$invokeAsync("exists", pair.getLeft(), pair.getRight());
        } catch (GenericException e) {
            log.error("1111");
        }
    }
}
