package org.okboom.wukong.push.stream.input;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
public class ValidateTopicInput implements Serializable {

    /**
     * 通知类型
     */
    private Integer notifyType;
    /**
     * 验证码
     */
    private String captcha;
}
