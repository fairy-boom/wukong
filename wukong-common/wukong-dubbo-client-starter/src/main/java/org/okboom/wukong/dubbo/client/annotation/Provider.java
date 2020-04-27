package org.okboom.wukong.dubbo.client.annotation;


import org.okboom.wukong.dubbo.client.constant.HttpMethodType;

import java.lang.annotation.*;

/**
 * @author tookbra
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Provider {


    /**
     * 接口路徑
     * @return
     */
    String path();

    /**
     * http method
     * @return
     */
    HttpMethodType httpMethod() default HttpMethodType.GET;
}
