package com.shengsiyuan.netty.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 类说明：NioTest
 *
 * @author zhucj
 * @since 20200423
 */
public class SelectorTest {

    public static void main(String[] args) throws IOException {
        testOne();
    }

    private static void testOne() throws IOException {

        int[] ports = getPorts();
        Selector selector = Selector.open();
        for (int i = 0; i < ports.length; i++) {

            ServerSocketChannel serverSocketChannel
                    = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);

            ServerSocket serverSocket = serverSocketChannel.socket();

            InetSocketAddress inetSocketAddress
                    = new InetSocketAddress(ports[i]);

            serverSocket.bind(inetSocketAddress);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口："+ ports[i]);
        }

        while (true) {
            int numbers = selector.select();
            System.out.println("numbers = " + numbers);

            Set<SelectionKey> selectionKeySet
                    = selector.selectedKeys();

            System.out.println("selectionKeySet : " + selectionKeySet);

            Iterator<SelectionKey> iterator
                    = selectionKeySet.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel
                            = (ServerSocketChannel)selectionKey.channel();

                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    iterator.remove();

                    System.out.println("获得客户端连接：" + socketChannel);

                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel
                            = (SocketChannel)selectionKey.channel();

                    int bytesRead = 0;
                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocate(512);
                        buffer.clear();

                        int read = socketChannel.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        buffer.flip();

                        socketChannel.write(buffer);
                        bytesRead += read;
                    }
                    System.out.println("读取：" + bytesRead +" ,  来自于 :" + socketChannel);

                    iterator.remove();
                }
            }
        }
    }

    private static int[] getPorts() {
        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;
        return ports;
    }
}
