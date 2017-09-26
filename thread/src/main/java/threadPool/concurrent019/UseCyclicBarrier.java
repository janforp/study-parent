package threadPool.concurrent019;
import java.io.IOException;  
import java.util.Random;  
import java.util.concurrent.BrokenBarrierException;  
import java.util.concurrent.CyclicBarrier;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors; 
public class UseCyclicBarrier {

	static class Runner implements Runnable {
	    private CyclicBarrier barrier;  
	    private String name;  
	    
	    Runner(CyclicBarrier barrier, String name) {
	        this.barrier = barrier;  
	        this.name = name;  
	    }  
	    @Override  
	    public void run() {  
	        try {  
	            Thread.sleep(1000 * (new Random()).nextInt(5));  
	            System.out.println(name + " 准备OK.");
	            //所有运动员都等待
	            barrier.await();  
	        } catch (InterruptedException | BrokenBarrierException e) {
	            e.printStackTrace();  
	        }
			System.out.println(name + " --- Go!!");
	    }  
	} 
	
    public static void main(String[] args) throws IOException, InterruptedException {
		//表示3个运动员（线程数）
        CyclicBarrier barrier = new CyclicBarrier(3);  // 3
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        //3个使用同一个 barrier,只要3个人都调用了 该 barrier 的 barrier.await();  方法，则都会继续往下执行
        executor.submit(new Thread(new Runner(barrier, "zhangsan")));  
        executor.submit(new Thread(new Runner(barrier, "lisi")));  
        executor.submit(new Thread(new Runner(barrier, "wangwu")));  
  
        executor.shutdown();  
    }  
  
}  