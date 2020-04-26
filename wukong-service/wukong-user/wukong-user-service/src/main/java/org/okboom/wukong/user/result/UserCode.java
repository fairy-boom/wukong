package org.okboom.wukong.user.result;

import org.okboom.wukong.dubbo.result.ServiceCode;

/**
 * 用户错误枚举
 * @author tookbra
 */
public enum UserCode implements ServiceCode {
    ;

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
