package org.okboom.wukong.sms;

import org.okboom.wukong.sms.api.stream.MtStreams;
import org.okboom.wukong.sms.connect.bean.CmppConnectBean;
import org.okboom.wukong.sms.domain.ChannelInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tookbra
 */
public class SmsApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        // 获取短信配置

        List<ChannelInfo> channelInfos = new ArrayList<>();

        channelInfos.forEach(t -> {
            CmppConnectBean connectBean = new CmppConnectBean();
        });

    }

    @StreamListener(MtStreams.INPUT_CMPP)
    public void handleMessage() {

    }
}
