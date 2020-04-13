package org.okboom.wukong.sms.common;

import io.netty.util.AttributeKey;
import org.okboom.wukong.sms.session.SessionState;

/**
 * @author tookbra
 */
public interface Constants {

    int DEFAULT_HEARTBEAT = 30;

    String IDLE_STATE_HANDLER_NAME = "IdleStateHandler";

    /**
     * 日期格式化
     */
    String MM_DD_HH_MM_SS = "MMddHHmmss";

    /**
     * 空数组
     */
    byte[] EMPTY_BYTES = new byte[0];

    /**
     * session状态channel属性
     */
    AttributeKey<SessionState> ATTRIBUTE_KEY = AttributeKey.newInstance(SessionState.Connect.name());
}
