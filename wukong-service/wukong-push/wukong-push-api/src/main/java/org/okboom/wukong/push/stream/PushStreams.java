package org.okboom.wukong.push.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author tookbra
 */
public interface PushStreams {

    String INPUT = "validate-topic-input";

    /**
     * input
     *
     * @return SubscribableChannel
     */
    @Input(INPUT)
    SubscribableChannel subscribableChannel();
}
