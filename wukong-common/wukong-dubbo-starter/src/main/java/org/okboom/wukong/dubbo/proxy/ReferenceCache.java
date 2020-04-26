package org.okboom.wukong.dubbo.proxy;


import org.apache.dubbo.config.ReferenceConfig;
import org.okboom.wukong.dubbo.domain.Metadata;

import java.util.List;

/**
 * @author tookbra
 */
public interface ReferenceCache {

    /**
     * 获取缓存数量
     * @return
     */
    int getSize();


    /**
     * 获取
     * @param key
     * @return
     */
    <T> ReferenceConfig<T> get(String key);

    /**
     * 移除单个缓存
     * @param key
     */
    void remove(String key);

    /**
     * 移除所有缓存
     */
    void removeAll();

    /**
     * 刷新
     * @param metadata
     */
    void refresh(List<Metadata> metadata);
}
