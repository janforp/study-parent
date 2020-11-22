package com.janita.datastructuresandalgorithms.bookofdjh.queue.test;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.BatQueueArray;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;
import org.junit.Assert;
import org.junit.Test;

/**
 * 顺序数组
 * 借助一个定长数组 Q 来存放对象，即可简单地实现队列。那么，为了符合 FIFO 准则，应该如 何表示和记录队列中各对象的次序呢?
 * 一种自然的办法就是仿照栈的实现，以 Q[0]作为队首，其它对象顺序往后存放。然而如此一来， 每次首元素出队之后，都需要将后续的所有元素向前顺移一个单元⎯⎯若队长为 n，这项工作需要 O(n)时间，因此效率很低。
 *
 * @author zhucj
 * @since 20201126
 */
public class BatQueueArrayTest {

    @Test
    public void testBatQueueArray() {
        Queue<Integer> queue = new BatQueueArray<>(3);
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.getSize());

        queue.enqueue(1);
        Assert.assertEquals(1, queue.getSize());

        queue.enqueue(2);
        Assert.assertEquals(2, queue.getSize());

        queue.enqueue(3);
        Assert.assertEquals(3, queue.getSize());

        ExceptionQueueFull full = null;
        try {
            queue.enqueue(4);
        } catch (Exception e) {
            full = (ExceptionQueueFull) e;
        }
        Assert.assertNotNull(full);

        Integer dequeue = queue.dequeue();
        Assert.assertEquals(1, (int) dequeue);
        Assert.assertEquals(2, queue.getSize());

        dequeue = queue.dequeue();
        Assert.assertEquals(2, (int) dequeue);
        Assert.assertEquals(1, queue.getSize());

        dequeue = queue.dequeue();
        Assert.assertEquals(3, (int) dequeue);
        Assert.assertEquals(0, queue.getSize());

        Assert.assertTrue(queue.isEmpty());

        ExceptionQueueEmpty empty = null;
        try {
            queue.dequeue();
        } catch (Exception e) {
            empty = (ExceptionQueueEmpty) e;
        }
        Assert.assertNotNull(empty);

        queue.enqueue(89);
        Assert.assertEquals(1, queue.getSize());

        Integer front = queue.front();
        Assert.assertEquals(89, (int) front);
    }
}
