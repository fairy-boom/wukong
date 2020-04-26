package org.okboom.wukong.push;

/**
 * @author tookbra
 */
public interface PushTemplate {


    /**
     * 发送消息
     * @param message
     */
    void sendMessage(String message);

    /**
     * 发送验证码
     * @param captcha
     */
    void sendValidate(String captcha);
}
