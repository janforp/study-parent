package com.janita.datastructuresandalgorithms.bookofdjh.queue.test;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.LoopQueue;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;
import org.junit.Assert;
import org.junit.Test;

/**
 * LoopQueueTest
 *
 * @author zhucj
 * @since 20201126
 */
public class LoopQueueTest {

    @Test
    public void test() {
        Queue<Integer> queue = new LoopQueue<>();
        Assert.assertTrue(queue.isEmpty());
        queue.enqueue(100);
        Assert.assertEquals(1, queue.getSize());
        Assert.assertEquals(100, (int) queue.dequeue());
        Assert.assertTrue(queue.isEmpty());
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }
}
