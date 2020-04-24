package org.okboom.wukong.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

/**
 * 异常过滤
 * @author tookbra
 */
@Slf4j
public class ExceptionFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("1111");
        return invoker.invoke(invocation);
    }
}
