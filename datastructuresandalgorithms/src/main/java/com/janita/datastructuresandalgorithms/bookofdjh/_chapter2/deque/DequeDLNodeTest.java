package com.janita.datastructuresandalgorithms.bookofdjh._chapter2.deque;

import org.junit.Assert;
import org.junit.Test;

/**
 * DequeDLNodeTest
 *
 * @author zhucj
 * @since 20201126
 */
public class DequeDLNodeTest {

    @Test
    public void test() {
        Deque<Integer> deque = new DequeDLNode<>();
        deque.insertFirst(9);
        deque.insertFirst(5);
        Assert.assertEquals(5, (int) deque.removeFirst());
        deque.insertLast(3);
        Assert.assertEquals(9, (int) deque.removeFirst());
        Assert.assertEquals(3, (int) deque.removeLast());
        RuntimeException exception = null;
        try {
            deque.removeFirst();
        } catch (RuntimeException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals("空了", exception.getMessage());
        Assert.assertTrue(deque.isEmpty());
        deque.insertLast(8);
        deque.insertLast(6);
        deque.insertFirst(3);
        Assert.assertEquals(3, (int) deque.getSize());
    }
}
