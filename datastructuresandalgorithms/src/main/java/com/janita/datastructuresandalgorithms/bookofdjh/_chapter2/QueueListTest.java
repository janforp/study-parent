package com.janita.datastructuresandalgorithms.bookofdjh._chapter2;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;
import org.junit.Assert;
import org.junit.Test;

/**
 * QueueListTest
 *
 * @author zhucj
 * @since 20201126
 */
public class QueueListTest {

    @Test
    public void testQueueList() {
        Queue<Integer> queue = new QueueList<>();
        queue.enqueue(1);
        queue.enqueue(2);
        Assert.assertEquals(1, (int) queue.dequeue());
        Assert.assertEquals(2, (int) queue.dequeue());
    }
}
