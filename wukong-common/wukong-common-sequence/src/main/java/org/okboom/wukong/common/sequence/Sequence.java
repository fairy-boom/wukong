package org.okboom.wukong.common.sequence;

import org.okboom.wukong.common.sequence.exception.SeqException;
import org.okboom.wukong.common.tool.annotation.MySPI;

/**
 * 序列号生成器接口
 * @author tookbra
 */
@MySPI
public interface Sequence {

    /**
     * 生成下一个序列号
     *
     * @return 序列号
     * @throws SeqException 序列号异常
     */
    long nextId() throws SeqException;

    /**
     * 下一个生成序号（带格式）
     *
     * @return
     * @throws SeqException
     */
    String formatNextId() throws SeqException;
}
