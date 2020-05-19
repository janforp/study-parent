package com.shengsiyuan.netty.nio.selector.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 类说明：NioChatServer
 * 通过 nc localhost 8899 当作客户端返回服务端即可测试
 *
 * @author zhucj
 * @since 20200423
 */
public class NioChatServer {

    private final static Map<String, SocketChannel> CLIENT_MAP = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Selector selector = getSelector();
        while (true) {
            try {
                //阻塞，直到关注的事件发生，返回关注的事件的数量
                //select() 方法仅仅是简单地将就绪的 IO 操作放到 selectedKeys 集合中, 因此如果我们从 selectedKeys 获取到一个 key,
                // 但是没有将它删除, 那么下一次 select 时, 这个 key 所对应的 IO 事件还在 selectedKeys 中.
                int select = selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                handlerEvent(selector, selectionKeySet);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Selector getSelector() throws IOException {
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
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null);
        return selector;
    }

    private static void handlerEvent(Selector selector, Set<SelectionKey> selectionKeySet) {
        selectionKeySet.forEach(selectionKey -> {
            SocketChannel client;
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
                    CLIENT_MAP.put(uuid(), client);
                }

                //有数据发送到服务端的事件
                else if (selectionKey.isReadable()) {
                    //因为只有该类型的channel注册了读取事件
                    client = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    //把客户端发来的数据读取到buffer中
                    int count = client.read(readBuffer);
                    if (count > 0) {
                        readBuffer.flip();
                        Charset utf8Charset = StandardCharsets.UTF_8;
                        char[] chars = utf8Charset.decode(readBuffer).array();
                        String receivedMessage = String.valueOf(chars);
                        System.out.println(client + " : ");
                        System.out.println(receivedMessage);
                        //分发所有的消息
                        dispatchMessage(client, receivedMessage);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //TODO 这一行很重要 为什么？
            //在每次迭代时, 我们都调用 "keyIterator.remove()" 将这个 key 从迭代器中删除, 因为 select() 方法仅仅是简单地将就绪的 IO 操作放到 selectedKeys 集合中,
            // 因此如果我们从 selectedKeys 获取到一个 key, 但是没有将它删除, 那么下一次 select 时, 这个 key 所对应的 IO 事件还在 selectedKeys 中.
            selectionKeySet.clear();
        });

    }

    private static void dispatchMessage(SocketChannel sendClient, String receivedMessage) throws IOException {
        String sendKey = null;
        for (Map.Entry<String, SocketChannel> entry : CLIENT_MAP.entrySet()) {
            String key = entry.getKey();
            SocketChannel value = entry.getValue();
            if (sendClient == value) {
                sendKey = key;
            }
        }
        for (Map.Entry<String, SocketChannel> entry : CLIENT_MAP.entrySet()) {
            SocketChannel socketChannel = entry.getValue();
            ByteBuffer writeBuffer = ByteBuffer.allocate(512);
            writeBuffer.put((sendKey + ": " + receivedMessage).getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }

    private static String uuid() {
        return "【" + UUID.randomUUID().toString() + "】";
    }
}
