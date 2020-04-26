package org.okboom.wukong.dubbo.result;

/**
 * 系统错误枚举
 * @author tookbra
 */
public enum SystemCode implements ServiceCode {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    ERROR(400, "业务异常"),
    /**
     * 参数校验失败
     */
    VALIDATION_ERROR(500,"参数校验失败");

    final int code;
    final String message;

    SystemCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
