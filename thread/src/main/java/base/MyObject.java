package base;

/**
 * 对象锁的同步和异步问题
 * @author alienware
 *
 */
public class MyObject {

	private synchronized void method1(){
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** synchronized */
	private void method2(){
			System.out.println(Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		//同一个对象
		final MyObject mo = new MyObject();
		/**
		 * 分析：
		 * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
		 * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
		 */
		Thread t1 = new Thread(mo::method1,"t1");
		Thread t2 = new Thread(mo::method2,"t2");
		t1.start();
		t2.start();
		
	}
	
}
