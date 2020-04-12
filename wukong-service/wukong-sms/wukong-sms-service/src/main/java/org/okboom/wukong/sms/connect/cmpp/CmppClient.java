package org.okboom.wukong.sms.connect.cmpp;

import io.netty.channel.ChannelPipeline;
import org.okboom.wukong.sms.connect.AbstractClient;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;


/**
 * @author tookbra
 */
public class CmppClient extends AbstractClient {


    public CmppClient(AbstractConnectBean abstractConnectBean) {
        super(abstractConnectBean);
    }

    @Override
    protected void doInitPipeLine(ChannelPipeline pipeline) {
        AbstractConnectBean abstractConnectBean = getConnectBean();

    }
}
