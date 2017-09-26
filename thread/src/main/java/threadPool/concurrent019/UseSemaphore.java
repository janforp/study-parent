package threadPool.concurrent019;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Semaphore;  
  
public class UseSemaphore {  
  
    public static void main(String[] args) {  
        // 线程池  
        ExecutorService exec = Executors.newCachedThreadPool();  
        // 只能5个线程同时访问  
        final Semaphore semaphore = new Semaphore(5);
        // 模拟20个客户端访问  
        for (int index = 0; index < 20; index++) {  
            final int NO = index;  
            Runnable run = () -> {
                try {
                    // 获取许可
                    semaphore.acquire();
                    System.out.println("Accessing: " + NO);
                    //模拟实际业务逻辑
                    Thread.sleep((long) (Math.random() * 10000));
                    // 访问完后，释放
                    semaphore.release();
                } catch (InterruptedException ignored) {
                }
            };
            exec.execute(run);  
        } 
        try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //System.out.println(semaphore.getQueueLength());
        // 退出线程池
        exec.shutdown();  
    }
}