package org.okboom.wukong.common.sequence.sequence;

import org.apache.commons.lang3.RandomUtils;
import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.common.sequence.exception.SeqException;
import org.okboom.wukong.common.tool.annotation.MySPI;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tookbra
 */
@MySPI("default")
public class DefaultSequence implements Sequence {

    private final static AtomicInteger sequenceId = new AtomicInteger(RandomUtils.nextInt());

    @Override
    public long nextId() throws SeqException {
        return sequenceId.incrementAndGet();
    }

    @Override
    public String formatNextId() throws SeqException {
        return String.valueOf(nextId());
    }
}
