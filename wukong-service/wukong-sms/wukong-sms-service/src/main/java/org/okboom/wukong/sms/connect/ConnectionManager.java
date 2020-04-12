package org.okboom.wukong.sms.connect;

import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tookbra
 */
public enum ConnectionManager {

    /**
     *
     */
    INSTANCE;

    public final Set<AbstractConnectBean> channels = new HashSet<>();

    /**
     *
     * @param abstractConnectBean
     */
    public void addConnect(AbstractConnectBean abstractConnectBean) {
        channels.add(abstractConnectBean);
    }

    /**
     * 连接所有短信网关
     */
    public void doOpenAll() {
        channels.forEach(t -> {
            doOpen(t);
        });
    }

    /**
     * 连接短信网关
     * @param abstractConnectBean
     */
    private void doOpen(AbstractConnectBean abstractConnectBean) {
        AbstractClient client = abstractConnectBean.getConnector();
        client.doOpen();
    }

}
