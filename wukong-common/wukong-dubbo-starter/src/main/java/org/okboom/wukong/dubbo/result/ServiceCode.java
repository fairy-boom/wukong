package org.okboom.wukong.dubbo.result;

import java.io.Serializable;

/**
 * @author tookbra
 */
public interface ServiceCode extends Serializable {

    /**
     *
     * @return
     */
    String getMessage();

    /**
     *
     * @return
     */
    int getCode();
}
