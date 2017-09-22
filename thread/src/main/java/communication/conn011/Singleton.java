package communication.conn011;

public class Singleton {
	//静态内部类
	private static class InnerSingleton {
		private static Singleton single = new Singleton();
	}
	
	public static Singleton getInstance(){
		return InnerSingleton.single;
	}
}
