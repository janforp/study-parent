package com.shengsiyuan.netty.nio.scatteringgathering;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 类说明：ScatteringGatheringTest
 *
 * @author zhucj
 * @since 20200423
 */
public class ScatteringGatheringTest {

    public static void main(String[] args) throws IOException {
        testOne();
    }

    private static void testOne() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.bind(address);

        int messageLen = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;
            while (bytesRead < messageLen) {
                long read = socketChannel.read(buffers);
                bytesRead += read;
                System.out.println("bytesRead = " + bytesRead);

                Arrays.stream(buffers)
                        .map(buffer -> "position：" + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.stream(buffers).forEach(ByteBuffer::flip);

            long bytesWrite = 0;
            while (bytesWrite < messageLen) {
                long write = socketChannel.write(buffers);
                bytesWrite += write;
            }

            Arrays.stream(buffers).forEach(ByteBuffer::clear);

            System.out.println("bytesRead: " + bytesRead + ",byteWrite: " + bytesWrite + ", messageLen = " + messageLen);
        }
    }
}
