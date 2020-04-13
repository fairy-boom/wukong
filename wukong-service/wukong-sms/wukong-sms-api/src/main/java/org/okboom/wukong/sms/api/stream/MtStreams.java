package org.okboom.wukong.sms.api.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author tookbra
 */
public interface MtStreams {
    String INPUT_CMPP = "cmpp_topic";

    /**
     * input
     *
     * @return SubscribableChannel
     */
    @Input(INPUT_CMPP)
    SubscribableChannel subscribableChannel();
}
