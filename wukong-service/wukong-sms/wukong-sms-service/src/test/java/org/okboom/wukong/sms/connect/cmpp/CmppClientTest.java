package org.okboom.wukong.sms.connect.cmpp;

import org.junit.Test;
import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.common.tool.extension.ExtensionLoader;
import org.okboom.wukong.sms.connect.ConnectionManager;
import org.okboom.wukong.sms.connect.bean.CmppConnectBean;

import java.util.concurrent.locks.LockSupport;

/**
 * @author tookbra
 */
public class CmppClientTest {


    @Test
    public void testConnect() {
        final Sequence sequence = ExtensionLoader.getExtensionLoader(Sequence.class)
                .getActivateExtension("default");

        ConnectionManager connectionManager = new ConnectionManager(sequence);
        CmppConnectBean cmppConnectBean = new CmppConnectBean();
        cmppConnectBean.setHost("127.0.0.1");
        cmppConnectBean.setPort(7890);
        cmppConnectBean.setWriteLimit(200);
        cmppConnectBean.setReadLimit(200);
        cmppConnectBean.setRetryConnect(3);
        cmppConnectBean.setLoginName("910000");
        cmppConnectBean.setLoginSecret("111111");
        cmppConnectBean.setSpNum("910084");
        cmppConnectBean.setMaxConnect(3);

        connectionManager.addConnect(cmppConnectBean);



        connectionManager.doOpenAll();

        LockSupport.park();
    }
}
