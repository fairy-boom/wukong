package org.okboom.wukong.sms.connect.bean;

import org.okboom.wukong.sms.connect.AbstractClient;
import org.okboom.wukong.sms.connect.cmpp.CmppClient;

/**
 * @author tookbra
 */
public class CmppConnectBean extends AbstractConnectBean {

    @Override
    protected AbstractClient buildConnector() {
        return new CmppClient(this);
    }
}
