package org.okboom.wukong.sms.connect;

import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;

/**
 * @author tookbra
 */
public abstract class AbstractEndpoint implements Client {

    private AbstractConnectBean abstractConnectBean;



    public AbstractEndpoint(AbstractConnectBean abstractConnectBean) {
        this.abstractConnectBean = abstractConnectBean;
    }

    @Override
    public AbstractConnectBean getConnectBean() {
        return abstractConnectBean;
    }
}
