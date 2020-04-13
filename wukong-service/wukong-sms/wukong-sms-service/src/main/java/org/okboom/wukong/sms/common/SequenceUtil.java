package org.okboom.wukong.sms.common;

import org.okboom.wukong.common.sequence.Sequence;
import org.okboom.wukong.common.sequence.exception.SeqException;
import org.okboom.wukong.common.sequence.sequence.DefaultSequence;

/**
 * @author tookbra
 */
public class SequenceUtil {

    private static final Sequence sequence = new DefaultSequence();

    public static long nextId() throws SeqException {
        return sequence.nextId();
    }
}
