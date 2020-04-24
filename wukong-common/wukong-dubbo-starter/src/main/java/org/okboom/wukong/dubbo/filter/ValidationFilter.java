package org.okboom.wukong.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 参数校验
 * @author tookbra
 */
@Slf4j
@Activate(order = -1)
public class ValidationFilter implements Filter {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("1111");
        return invoker.invoke(invocation);
    }
}
