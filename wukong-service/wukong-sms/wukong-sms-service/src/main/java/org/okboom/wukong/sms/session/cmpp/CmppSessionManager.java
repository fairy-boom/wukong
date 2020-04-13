package org.okboom.wukong.sms.session.cmpp;

import cn.hutool.core.date.DateUtil;
import com.google.common.primitives.Bytes;
import io.netty.channel.Channel;
import org.apache.commons.codec.digest.DigestUtils;
import org.okboom.wukong.sms.common.Constants;
import org.okboom.wukong.sms.common.SequenceUtil;
import org.okboom.wukong.sms.connect.bean.AbstractConnectBean;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppConnectMessage;
import org.okboom.wukong.sms.exchange.protocol.cmpp.CmppConnectRespMessage;
import org.okboom.wukong.sms.session.AbstractSessionManager;

import java.time.LocalDateTime;

/**
 * 移动短信session控制器
 * @author tookbra
 */
public class CmppSessionManager extends AbstractSessionManager {

    public CmppSessionManager(AbstractConnectBean abstractConnectBean) {
        super(abstractConnectBean);
    }

    @Override
    protected void doLogin(Channel channel) {
        CmppConnectMessage connectRequestMessage = new CmppConnectMessage();
        connectRequestMessage.setSourceAddr(abstractConnectBean.getSpNum());
        connectRequestMessage.setSeqId((int)SequenceUtil.nextId());
        String timestamp = DateUtil.format(LocalDateTime.now(), Constants.MM_DD_HH_MM_SS);
        byte[] loginNameBytes = abstractConnectBean.getLoginName().getBytes();
        byte[] loginSecretBytes = abstractConnectBean.getLoginSecret().getBytes();
        byte[] timestampBytes = timestamp.getBytes();
        connectRequestMessage.setAuthenticatorSource(DigestUtils.md5(Bytes.concat(loginNameBytes, new byte[9],
                loginSecretBytes, timestampBytes)));
        connectRequestMessage.setTimestamp(Integer.parseInt(timestamp));
        connectRequestMessage.setVersion((short)0x30);
        channel.writeAndFlush(connectRequestMessage);
    }

    @Override
    protected int validLoginMsg(Object msg) {
        CmppConnectRespMessage resp = (CmppConnectRespMessage) msg;
        return (int)resp.getStatus();
    }
}
