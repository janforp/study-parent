package com.shengsiyuan.netty.nio.selector.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：NitChatClient
 *
 * @author zhucj
 * @since 20200423
 */
public class NioChatClient {

    public static void main(String[] args) {
        try {
            Selector selector = getSelector();
            while (true) {
                //阻塞，一旦有任何事件，就往下执行
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeySet) {
                    //可连接的，即建立连接事件
                    handleEvent(selector,selectionKey);
                }
                //清除已经处理完的事件
                selectionKeySet.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Selector getSelector() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //不要阻塞
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        //客户端向服务端发起连接，那么对于客户端来说就是连接事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));
        return selector;
    }

    private static void handleEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable()) {
            SocketChannel client = (SocketChannel) selectionKey.channel();
            //连接是否正在进行中
            if (client.isConnectionPending()) {
                //完成连接,表示连接真正的建立成功了 TODO 是不是三次握手的一步？
                client.finishConnect();

                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                writeBuffer.flip();
                client.write(writeBuffer);

                ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                executorService.submit(() -> {
                    while (true) {
                        try {
                            writeBuffer.clear();
                            //键盘输入
                            InputStreamReader input = new InputStreamReader(System.in);
                            BufferedReader reader = new BufferedReader(input);
                            String sendMessage = reader.readLine();
                            writeBuffer.put(sendMessage.getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            SocketChannel client = (SocketChannel) selectionKey.channel();
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            int read = client.read(writeBuffer);
            if (read > 0) {
                String msgFromServer = new String(writeBuffer.array(), 0, read);
                System.out.println(msgFromServer);
            }
        }
    }
}
