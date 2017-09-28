package aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	//服务器通道
	AsynchronousServerSocketChannel channel;
	
	private Server(int port){
		try {
			//创建一个缓存池
			ExecutorService executorService = Executors.newCachedThreadPool();
			//创建线程组
			AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
			//创建服务器通道
			channel = AsynchronousServerSocketChannel.open(threadGroup);
			//进行绑定
			channel.bind(new InetSocketAddress(port));
			
			System.out.println("server start , port : " + port);
			//进行阻塞
			channel.accept(this, new ServerCompletionHandler());
			//一直阻塞 不让服务器停止
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(8765);
	}
}
