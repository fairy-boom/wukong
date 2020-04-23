package org.okboom.wukong.common.tool.convert;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 基础转换类
 * @author tookbra
 */
@MapperConfig
public interface BaseConvert<S,T> {

    /**
     * 同属性映射转换
     * @param s 转换源
     * @return 转换目标
     */
    @Mappings({})
    T convertToTarget(S s);

    /**
     * 同属性映射转换
     * @param t 转换源
     * @return
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    S convertToSource(T t);

    /**
     * 同属性映射转换
     * @param s
     * @return
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<T> convertTo(List<S> s);

    /**
     * 同属性映射转换
     * @param t
     * @return
     */
    @InheritConfiguration(name = "targetToSource")
    List<S> targetToSource(List<T> t);
}
