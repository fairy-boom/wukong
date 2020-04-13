package org.okboom.wukong.common.tool.annotation;

import java.lang.annotation.*;

/**
 * @author tookbra
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MySPI {

    String value() default "";
}
