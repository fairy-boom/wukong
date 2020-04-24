package org.okboom.wukong.dubbo.result;

import java.io.Serializable;

/**
 * dubbo服务统一返回对象
 * @author tookbra
 */
public final class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = -2946020128125115477L;

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回对象
     */
    private T data;
    /**
     * 返回信息
     */
    private String msg;

    /**
     * 构造
     * @param serviceCode
     * @param msg
     */
    public ServiceResult(ServiceCode serviceCode, String msg) {
        this(serviceCode, null, msg);
    }

    /**
     * 构造
     * @param serviceCode
     * @param data
     */
    private ServiceResult(ServiceCode serviceCode, T data) {
        this(serviceCode, data, serviceCode.getMessage());
    }

    /**
     * 构造
     * @param serviceCode
     * @param data
     * @param msg
     */
    private ServiceResult(ServiceCode serviceCode, T data, String msg) {
        this(serviceCode.getCode(), data, msg);
    }

    /**
     * 构造
     * @param code
     * @param data
     * @param msg
     */
    private ServiceResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = SystemCode.SUCCESS.code == code;
    }


    /**
     * 失败
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServiceResult<T> fail(String msg) {
        return new ServiceResult(SystemCode.ERROR, msg);
    }

    /**
     * 失败
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServiceResult<T> fail(int code, String msg) {
        return new ServiceResult(code, null, msg);
    }

    /**
     * 成功
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServiceResult<T> success(String msg) {
        return new ServiceResult(SystemCode.SUCCESS, msg);
    }

}
