package org.okboom.wukong.dubbo.cache;

import com.google.common.collect.Maps;
import org.okboom.wukong.dubbo.domain.Metadata;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * @author tookbra
 */
public class ApplicationCache {

    /**
     * path-> MetaData.
     */
    private static final ConcurrentMap<String, Metadata> META_DATA = Maps.newConcurrentMap();

    public void updateMeta(Metadata metadata) {
        Set<String> set = new HashSet<>(META_DATA.keySet());
        set.remove(metadata.getPath());
        META_DATA.put(metadata.getPath(), metadata);

        if (!set.isEmpty()) {
            META_DATA.keySet().removeAll(set);
        }
    }
}
