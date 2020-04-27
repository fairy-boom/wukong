package org.okboom.wukong.dubbo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.listener.ExporterListenerAdapter;
import org.okboom.wukong.dubbo.cache.ReferenceCache;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务暴露/下线监听
 * @author tookbra
 */
@Slf4j
@Activate(value = "cacheExporterListener", order = 99)
public class CacheExporterListener extends ExporterListenerAdapter {

    @Autowired
    ReferenceCache referenceCache;


    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        super.exported(exporter);
    }

    @Override
    public void unexported(Exporter<?> exporter) throws RpcException {
        final String serviceName = exporter.getInvoker().getInterface().getName();
        referenceCache.remove(serviceName);
    }
}
