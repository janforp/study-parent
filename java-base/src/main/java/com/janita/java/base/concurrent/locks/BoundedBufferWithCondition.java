package com.janita.java.base.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：实现一个阻塞队列
 *
 * @author zhucj
 * @since 20200423
 */
public class BoundedBufferWithCondition {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private final Object[] items = new Object[5];

    private int
            //下一个放入元素的下标
            putPtr = 0,
    //下一个读取元素的下标
    takePtr = 0,
    //实际有的内容
    count = 0;

    /**
     * 试图放进去，如果满了，则等待有空的位置
     *
     * @param e 被放入的元素
     */
    public void put(Object e) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putPtr] = e;
            if (++putPtr == items.length) {
                putPtr = 0;
            }
            ++count;
            //只要放如一个元素就告诉其他线程，该队列不是空的啦，意思就是可以取了；
            //我已经放完了，唤醒取的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
            System.out.println("【put】" + e);
        }
    }

    /**
     * 试图取出一个元素，如果空了，则等待一个元素被放进去之后再取
     *
     * @return 被去除的元素
     */
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object e = items[takePtr];
            if (++takePtr == items.length) {
                takePtr = 0;
            }
            --count;
            //告诉其他线程，队列没有满了，意思就是可以存了
            //我已经取完了，唤醒存的线程
            notFull.signal();
            System.out.println("【take】" + items[count]);
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBufferWithCondition buffer = new BoundedBufferWithCondition();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    buffer.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    buffer.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
