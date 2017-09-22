package communication.conn011;

public class DoubleSingleton {

	private static DoubleSingleton ds;
	
	public  static DoubleSingleton getDs(){
		if(ds == null){
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//第二次判断
			synchronized (DoubleSingleton.class) {
				if(ds == null){
					ds = new DoubleSingleton();
				}
			}

			// TODO 为什么还要加一个判断
//			synchronized (DoubleSingleton.class) {
//				ds = new DoubleSingleton();
//			}
		}
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> System.out.println(DoubleSingleton.getDs().hashCode()),"t1");
		Thread t2 = new Thread(() -> System.out.println(DoubleSingleton.getDs().hashCode()),"t2");
		Thread t3 = new Thread(() -> System.out.println(DoubleSingleton.getDs().hashCode()),"t3");
		t1.start();
		t2.start();
		t3.start();
	}
	
}
