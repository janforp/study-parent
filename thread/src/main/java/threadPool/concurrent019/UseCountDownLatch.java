package threadPool.concurrent019;
import java.util.concurrent.CountDownLatch;
public class UseCountDownLatch {

	/**
	 * 使用场景:
	 * 正如每个Java文档所描述的那样，CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行
	 * 例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 参数2：表示要得到几次通知才能唤醒等待的线程
		 */
		final CountDownLatch countDown = new CountDownLatch(2);
		Thread t1 = new Thread(() -> {
            try {
                System.out.println("进入线程t1" + "等待其他线程处理完成...");
                countDown.await();//在此阻塞，通过之后才往下执行
				while (true) {
					Thread.sleep(1000);
					System.out.println("t1线程继续执行...");
				}
			} catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
		
		Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2线程进行初始化操作...");
                Thread.sleep(3000);
                System.out.println("t2线程初始化完毕，通知t1线程继续...");
                countDown.countDown();//一个
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
		Thread t3 = new Thread(() -> {
            try {
                System.out.println("t3线程进行初始化操作...");
                Thread.sleep(4000);
                System.out.println("t3线程初始化完毕，通知t1线程继续...");
                countDown.countDown();//2个
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
		t1.start();
		t2.start();
		t3.start();
	}
}
