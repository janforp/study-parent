public class RunThread extends Thread{
	// main 线程跟 rt 线程都能公用，其他线程
	private volatile boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	public void run(){
		System.out.println("进入run方法..");
		while(isRunning){}
		System.out.println("线程停止");
	}
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		//main 线程也能使用 isRunning
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);

	}
}
