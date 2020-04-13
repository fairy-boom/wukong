package org.okboom.wukong.sms.connect.cmpp;

import io.netty.channel.ChannelPipeline;
import org.okboom.wukong.sms.connect.AbstractClient;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;
import org.okboom.wukong.sms.handler.cmpp.CmppIdleStateHandler;
import org.okboom.wukong.sms.session.cmpp.CmppSessionManager;


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
        pipeline.addLast("CmppServerIdleStateHandler", new CmppIdleStateHandler());
        pipeline.addLast("Codec", new CmppCodecChannelInitializer());
        pipeline.addLast("CmppSessionLoginManager", new CmppSessionManager(abstractConnectBean));
    }

    @Override
    protected void doBindHandler(ChannelPipeline pipeline) {

    }
}
