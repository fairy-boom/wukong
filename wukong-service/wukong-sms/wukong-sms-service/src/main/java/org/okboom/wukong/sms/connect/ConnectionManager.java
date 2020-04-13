package org.okboom.wukong.sms.connect;

import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tookbra
 */
public final class ConnectionManager {


    public final Set<AbstractConnectBean> channels = new HashSet<>();

    private final Sequence sequence;

    public ConnectionManager(Sequence sequence) {
        this.sequence = sequence;
    }

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
