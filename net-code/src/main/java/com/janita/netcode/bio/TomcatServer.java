package com.janita.netcode.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：单线程支持并发
 *
 * java ---> jni ---->
 *
 * @author zhucj
 * @since 20200423
 */
public class TomcatServer {

    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    /**
     * 客户端链接
     */
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9098);
        serverSocket.bind(socketAddress);
        //设置为非阻塞
        serverSocket.configureBlocking(false);

        while (true) {
            //处理旧连接的请求
            for (SocketChannel socketChannel : channelList) {
                int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    System.out.println("read  = " + read);
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(content);
                    byteBuffer.flip();
                } else if (read == -1) {
                    channelList.remove(socketChannel);
                }
            }

            //接收新请求
            SocketChannel accept = serverSocket.accept();
            if (accept != null) {
                System.out.println("conn success");
                accept.configureBlocking(false);
                channelList.add(accept);
                System.out.println(channelList.size() + "list --- size");
            }
        }
    }
}
