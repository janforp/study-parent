package javaapi;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * WaitNode
 *
 * @author zhucj
 * @since 20210225
 */
public class WaitNode {

    volatile Thread thread;

    volatile WaitNode next;

    volatile int no;

    WaitNode() {
        thread = Thread.currentThread();
    }

    public WaitNode(int no) {
        this();
        this.no = no;
    }

    @Override
    public String toString() {
        return "WaitNode{" +
                "no=" + no +
                '}';
    }
}

class Test {

    public static void main(String[] args) {
        Test test = new Test();
        WaitNode node1 = new WaitNode(1);
        WaitNode node2 = new WaitNode(2);
        WaitNode node3 = new WaitNode(3);
        WaitNode node4 = new WaitNode(4);
        WaitNode node5 = new WaitNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        test.waiters = node1;

        node3.thread = null;

        test.removeWaiter(node4);
        System.out.println(test.waiters);
    }

    /**
     * Treiber stack of waiting threads
     */
    //因为会有很多线程去get当前任务的结果，所以 这里使用了一种数据结构 stack 头插 头取 的一个队列。
    private volatile WaitNode waiters;

    private void removeWaiter(WaitNode node) {
        if (node != null) {

            //先把节点的 thread 属性设置为 null
            node.thread = null;
            retry:
            for (; ; ) {// restart on removeWaiter race 重新启动removeWaiter竞赛

                for (
                    //当前节点的上一个
                        WaitNode pred = null,
                        //当前节点
                        q = waiters,
                        //s = q.next;当前节点的下一个节点
                        s;

                    //循环结束条件，当前节点不为null
                        q != null;

                    //下次循环的起点，把当前节点指向下一个节点
                        q = s) {

                    //赋值为当前节点的下一个节点
                    s = q.next;

                    if (q.thread != null) {
                        pred = q;
                    } else if (pred != null) {
                        pred.next = s;
                        if (pred.thread == null) {
                            // check for race
                            continue retry;
                        }
                    } else if (!UNSAFE.compareAndSwapObject(this, waitersOffset, q, s)) {
                        continue retry;
                    }
                }

                break;
            }
        }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;

    private static final long waitersOffset;

    static {
        try {
            UNSAFE = getUnsafe();
            Class<?> k = Test.class;
            waitersOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("waiters"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private static Unsafe INSTANCE;

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (Test.class) {
            if (INSTANCE == null) {
                //                INSTANCE = reflectGetUnsafe();
                INSTANCE = getUnsafeFromField();
            }
        }
        return INSTANCE;
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static Unsafe getUnsafeFromField() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }
}
