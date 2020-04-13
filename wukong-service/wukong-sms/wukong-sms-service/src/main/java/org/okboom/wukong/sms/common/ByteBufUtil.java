package org.okboom.wukong.sms.common;

import cn.hutool.core.util.CharsetUtil;
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

    /**
     * 字符串转数组
     * @param value
     * @param length
     * @return
     */
    public static byte[] str2bytes(String value, Integer length) {
        byte[] values = value.getBytes(CharsetUtil.CHARSET_UTF_8);
        if(values.length == length) {
            return values;
        }
        return copyOf(values, length);
    }

    private static byte[] copyOf(byte[] original, int length) {
        byte[] copy = new byte[length];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, length));
        return copy;
    }
}
