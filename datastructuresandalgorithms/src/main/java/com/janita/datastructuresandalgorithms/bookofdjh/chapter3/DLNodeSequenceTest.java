package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * DLNodeSequenceTest
 *
 * @author zhucj
 * @since 20201126
 */
public class DLNodeSequenceTest {

    @Test
    public void test() {
        DLNodeSequence<Integer> sequence = new DLNodeSequence<>();
        for (int i = 9; i >= 0; i--) {
            sequence.insertFirst(i);
        }
        System.out.println(sequence.rank2Pos2(0).getElem());
        System.out.println(sequence.rank2Pos2(3).getElem());
        System.out.println(sequence.rank2Pos2(7).getElem());
        System.out.println(sequence.rank2Pos2(9).getElem());
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(sequence.rank2Pos(i), sequence.rank2Pos2(i));
        }
    }
}