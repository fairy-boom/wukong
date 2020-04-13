package org.okboom.wukong.common.sequence;

import org.junit.Test;
import org.okboom.wukong.common.sequence.sequence.DefaultSequence;

/**
 * @author tookbra
 */
public class DefaultSequenceTest {

    @Test
    public void testGetSeq() {
        Sequence sequence = new DefaultSequence();
        System.out.println(sequence.nextId());
    }
}
