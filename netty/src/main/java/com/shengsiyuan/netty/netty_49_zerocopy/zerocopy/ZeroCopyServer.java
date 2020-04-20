package com.shengsiyuan.netty.netty_49_zerocopy.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 类说明：ZeroCopyServer
 *
 * @author zhucj
 * @since 20200423
 */
public class ZeroCopyServer {

    public static void main(String[] args) throws Exception {

        InetSocketAddress address = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        //Enabling {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} prior to
        //binding the socket using {@link #bind(SocketAddress)} allows the socket
        //to be bound even though a previous connection is in a timeout state.
        //在绑定之前调用有效
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int readCount = 0;

            while (-1 != readCount) {
                readCount = socketChannel.read(byteBuffer);

                //重复读，position 归位
                byteBuffer.rewind();
            }
        }
    }
}
