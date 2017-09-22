package conn010;

public class ConnThreadLocal {
	//每一个线程单独一个
	private static ThreadLocal<String> th = new ThreadLocal<>();
	
	private void setTh(String value){
		th.set(value);
	}
	private void getTh(){
		System.out.println(Thread.currentThread().getName() + ":" + th.get());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(() -> {
            ct.setTh("张三");
            ct.getTh();
        }, "t1");
		
		Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                ct.getTh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
		t1.start();
		t2.start();
	}
}
