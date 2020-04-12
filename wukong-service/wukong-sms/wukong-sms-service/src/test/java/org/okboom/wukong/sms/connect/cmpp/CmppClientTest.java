package org.okboom.wukong.sms.connect.cmpp;

import org.junit.Test;
import org.okboom.wukong.sms.connect.ConnectionManager;
import org.okboom.wukong.sms.connect.bean.CmppConnectBean;

import java.util.concurrent.locks.LockSupport;

/**
 * @author tookbra
 */
public class CmppClientTest {


    @Test
    public void testConnect() {
        ConnectionManager connectionManager = ConnectionManager.INSTANCE;
        CmppConnectBean cmppConnectBean = new CmppConnectBean();
        cmppConnectBean.setHost("127.0.0.1");
        cmppConnectBean.setPort(7890);
        cmppConnectBean.setLimit(200);
        cmppConnectBean.setLoginName("910000");
        cmppConnectBean.setLoginSecret("111111");
        cmppConnectBean.setSpNum("910084");

        connectionManager.addConnect(cmppConnectBean);
        connectionManager.doOpenAll();

        LockSupport.park();
    }
}
