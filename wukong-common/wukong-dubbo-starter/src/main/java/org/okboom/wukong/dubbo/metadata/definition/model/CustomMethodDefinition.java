package org.okboom.wukong.dubbo.metadata.definition.model;

import lombok.Data;
import org.apache.dubbo.metadata.definition.model.MethodDefinition;

/**
 * @author tookbra
 * @date 2020/4/29
 * @description
 */
@Data
public class CustomMethodDefinition extends MethodDefinition {

    private static final long serialVersionUID = 956483387789536368L;
    /**
     * rest路径
     */
    private String path;
}
