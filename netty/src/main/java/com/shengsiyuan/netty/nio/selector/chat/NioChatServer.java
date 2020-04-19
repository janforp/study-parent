package com.shengsiyuan.netty.nio.selector.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 类说明：NioChatServer
 *
 * @author zhucj
 * @since 20200423
 */
public class NioChatServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //服务器端的socket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        //监听
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //把 ServerSocketChannel 注册到 selector
        //关注连接事件,因为开始肯定是连接，只有建立连接之后才能发生读写事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                //阻塞，直到关注的事件发生，返回关注的事件的数量
                int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        //如果是客户端向服务端连接的事件，则需要服务端接受连接，并且把通道对象注册到选择器
                        if (selectionKey.isAcceptable()) {
                            //创建连接的通道,因为只有该类型的通道注册了该类型的事件
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            //获取与客户端的连接通道
                            client = server.accept();
                            //一旦链接建立，把 client 注册到 selector
                            client.configureBlocking(false);
                            //注册到多路复用器,关注读事件
                            client.register(selector, SelectionKey.OP_READ);
                            clientMap.put(uuid(), client);
                        }

                        //有数据发送到服务端的事件
                        if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String uuid() {
        return "【" + UUID.randomUUID().toString() + "】";
    }

}
