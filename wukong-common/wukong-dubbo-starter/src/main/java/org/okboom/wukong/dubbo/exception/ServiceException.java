package org.okboom.wukong.dubbo.exception;

/**
 * 服务异常类
 * @author tookbra
 */
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -2516587712135293227L;

    /**
     * 错误码
     */
    private Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
