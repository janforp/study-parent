package threadPool.concurrent019;

import java.util.concurrent.*;

public class UseFuture implements Callable<String>{
	private String para;
	
	public UseFuture(String para){
		this.para = para;
	}
	
	/**
	 * 这里是真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		//模拟执行耗时
		Thread.sleep(5000);
		return this.para + "处理完成";
	}
	
	//主控制函数
	public static void main(String[] args) throws Exception {
		String queryStr = "query";
		//构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
		FutureTask<String> future = new FutureTask<>(new UseFuture(queryStr));
		FutureTask<String> futureTask = new FutureTask<>(new UseFuture("第二个请求参数"));
		//创建一个固定线程的线程池且线程数为1,
		ExecutorService executor = Executors.newFixedThreadPool(2);
		//这里提交任务future,则开启线程执行RealData的call()方法执行
		/**
		 * submit 与 execute 的区别:
		 * 1:submit 可以传入实现 Callable 接口实 现的对象，
		 * 2:submit 有返回值
		 *
		 * 下面2行代码表示启动了2个线程去执行任务
		 */
		executor.submit(future);
		executor.submit(futureTask);
		//若返回 null,则说明该任务已经完成
//		f.get();
		System.out.println("请求完毕");
		//异步调用执行结果
		try {
			//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("处理其他业务逻辑");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 并行操作的
		 * 但是如果get不到结果会一直阻塞
		 */
		System.out.println("数据：" + future.get());
		System.out.println("数据：" + futureTask.get());
		System.out.println("结果数据都取到了");

		//调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
		executor.shutdown();
	}
}
