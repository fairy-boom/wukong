package org.okboom.wukong.sms.common;

import io.netty.buffer.ByteBuf;

/**
 * @author tookbra
 */
public class ByteBufUtil {

    /**
     * convert ByteBuf to byte[]
     * @param buf
     * @param length
     * @return
     */
    public static byte[] toArray(ByteBuf buf, int length){
        byte[] result = new byte[length];
        buf.readBytes(result);
        return result;
    }
}
