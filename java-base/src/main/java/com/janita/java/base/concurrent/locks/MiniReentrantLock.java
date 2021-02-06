package com.janita.java.base.concurrent.locks;

import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * MiniReentrantLock
 *
 * @author zhucj
 * @since 20210225
 */
@SuppressWarnings("all")
public class MiniReentrantLock implements MiniLock {

    /**
     * 锁资源
     * 0:未加锁状态
     * >0：加锁状态
     */
    @Getter
    private volatile int state;

    /**
     * 独占模式：同一时刻只有一个线程可以持有锁，其他线程在未获取到锁的时候会被阻塞
     *
     * 当前独占锁的线程(占用锁的线程)
     */
    @Getter
    private Thread exclusiveOwnerThread;

    /**
     * 头节点：head节点对应的线程，就是当前占用锁的线程
     */
    @Getter
    private Node head;

    private Node tail;

    /**
     * 公平锁
     *
     * lock的过程
     * 情景1：线程进来发现state == 0,则直接抢到锁
     * 情景2：线程进来发现state > 0,这个时候当前线程入队
     */
    @Override
    public void lock() {
        acquire(1);
    }

    @Override
    public void unlock() {
        release(1);
    }

    private void release(int arg) {
        if (tryRelease(arg)) {
            Node head = this.head;
            if (head.next != null) {
                //公平锁，唤醒 head.next 节点
                unparkSuccessor(head);
            }
        }
    }

    private void unparkSuccessor(Node node) {
        Node s = node.next;
        if (s != null && s.thread != null) {
            LockSupport.unpark(s.thread);
        }
    }

    private boolean tryRelease(int arg) {
        int c = getState() - arg;
        if (getExclusiveOwnerThread() != Thread.currentThread()) {
            throw new RuntimeException("fuck you! must getLock!");
        }
        //下面就没有并发了

        if (c == 0) {
            this.exclusiveOwnerThread = null;
            this.state = c;
            return true;
        }
        this.state = c;
        return false;
    }

    /**
     * 竞争资源
     * 1.尝试获取锁，成功则占用
     * 2.抢占锁失败，阻塞当前线程
     */
    private void acquire(int args) {
        if (!tryAcquire(args)) {
            Node node = addWaiter();
            acquireQueued(node, args);
        }
    }

    private void acquireQueued(Node node, int arg) {
        for (; ; ) {
            Node pred = node.prev;
            if (pred == head && tryAcquire(arg)) {
                setHead(node);
                pred.next = null;
                return;
            }
            //挂起当前线程
            System.out.println("线程：" + Thread.currentThread().getName() + "， 挂起");
            LockSupport.park();
            System.out.println("线程：" + Thread.currentThread().getName() + "， 唤醒");
        }
    }

    private Node addWaiter() {
        Node newNode = new Node(Thread.currentThread());
        Node pred = tail;
        if (pred != null) {
            newNode.prev = pred;
            if (compareAndSetTail(pred, tail)) {
                pred.next = newNode;
                return newNode;
            }
        }
        enq(newNode);
        return newNode;
    }

    /**
     * 自旋入队
     * 只有成功后才返回
     *
     * @param node
     * @return
     */
    private void enq(Node node) {
        for (; ; ) {
            if (tail == null) {
                if (compareAndSetHead(new Node())) {
                    tail = head;
                }
            } else {
                Node pred = tail;
                if (pred != null) {
                    node.prev = tail;
                    if (compareAndSetTail(pred, node)) {
                        pred.next = node;
                        return;
                    }
                }
            }
        }
    }

    /**
     * 尝试获取锁
     *
     * @return 抢占成功返回true, 否则返回false
     */
    private boolean tryAcquire(int args) {
        if (state == 0) {
            if (!hasQueuedPredecessor() && compareAndSetState(0, args)) {
                this.exclusiveOwnerThread = Thread.currentThread();
                return true;
            }
        } else if (Thread.currentThread() == this.exclusiveOwnerThread) {
            //此分支是没有并发的
            //锁重入
            int c = getState();
            c = c + args;
            this.state = c;
            return true;
        }
        return false;
    }

    /**
     * 返回当前线程前面是否有等待线程
     * lock -> acquire -> tryAcquire -> hasQueuedPredecessor
     *
     * @see lock
     */
    private boolean hasQueuedPredecessor() {
        Node h = this.head;
        Node t = this.tail;
        Node s;
        return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
    }

    private void setHead(Node node) {
        this.head = node;
        //TODO 因为当前节点已经是获取锁成功的线程了？
        node.thread = null;
        node.prev = null;
    }

    /**
     * 阻塞的线程被封装成Node节点，放入到FIFO队列
     */
    static final class Node {

        Node prev;

        Node next;

        /**
         * 封装的线程本尊
         */
        Thread thread;

        public Node() {
        }

        public Node(Thread thread) {
            this.thread = thread;
        }
    }

    private static final Unsafe unsafe;

    private static final long stateOffset;

    private static final long headOffset;

    private static final long tailOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            stateOffset = unsafe.objectFieldOffset(MiniReentrantLock.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(MiniReentrantLock.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(MiniReentrantLock.class.getDeclaredField("tail"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapObject(this, state, expect, update);
    }
}