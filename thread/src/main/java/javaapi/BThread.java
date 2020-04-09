package javaapi;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
class BThread extends Thread {

    /**
     * BThread
     */
    public BThread() {
        super("[BThread] Thread");
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(threadName + " loop at " + i);
                Thread.sleep(1000);
            }
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }
}

/**
 * AThread
 */
class AThread extends Thread {

    /**
     * bt
     */
    BThread bt;

    /**
     * AThread
     *
     * @param bt bt
     */
    public AThread(BThread bt) {
        super("[AThread] Thread");
        this.bt = bt;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        try {
            bt.join();
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }
}

/**
 * TestDemo
 */
class TestDemo {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        BThread bt = new BThread();
        AThread at = new AThread(bt);
        try {
            bt.start();
            Thread.sleep(2000);
            at.start();
            at.join();
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
    }
}