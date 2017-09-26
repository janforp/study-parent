package threadPool.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 一般拒绝策略的时候是放入log，在非高峰期的时候进行解析处理
 * 而不是放到缓存，因为拒绝的原因就是空间不足，放到缓存有什么意义呢
 */
public class MyRejected implements RejectedExecutionHandler{

	
	public MyRejected(){
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString());
	}

}
