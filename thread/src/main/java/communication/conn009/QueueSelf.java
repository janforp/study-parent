package communication.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Janita on 2017/9/22 0022-下午 1:29
 * 该类是：
 */
public class QueueSelf {

    //1.需要一个装元素的集合
    private final LinkedList<Object> list = new LinkedList<>();
    //2.计数器，统计元素的个数
    private AtomicInteger count = new AtomicInteger(0);
    //3.需要指定上限及下限
    private int minSize = 0;
    private int maxSize ;

    public QueueSelf(int size) {
        this.maxSize = size;
    }

    //初始化一个对象，用于加锁
    private final Object lock = new Object();

    public void put(Object obj) {
        synchronized (lock) {
            //当前容器满了
            while (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.incrementAndGet();
            System.out.println("新加入的元素: " + obj);
            //通知 take 线程可以取了
            lock.notify();
        }
    }

    public Object take() {
        Object ret;
        synchronized (lock) {
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ret = list.removeFirst();
            count.decrementAndGet();
            //唤醒另外一个线程
            lock.notify();
        }
        return ret;
    }

    public int getSize() {
        return this.count.get();
    }

    public static void main(String[] args) {
        QueueSelf queueSelf = new QueueSelf(5);
        queueSelf.put("a");
        queueSelf.put("b");
        queueSelf.put("c");
        queueSelf.put("d");
        queueSelf.put("e");

        System.out.println("当前容器的长度: " + queueSelf.getSize());

        Thread t1 = new Thread(() -> {
            queueSelf.put("f");
            queueSelf.put("g");
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() ->{
            Object o1 = queueSelf.take();
            System.out.println("移除的元素为: " + o1);
            Object o2 = queueSelf.take();
            System.out.println("移除的元素为: " + o2);
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
