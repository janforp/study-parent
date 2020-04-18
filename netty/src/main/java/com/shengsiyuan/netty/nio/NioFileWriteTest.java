package com.shengsiyuan.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明：NioTest3
 *
 * @author zhucj
 * @since 20200423
 */
public class NioFileWriteTest {

    public static void main(String[] args) throws IOException {

        FileOutputStream outputStream = new FileOutputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/NioTest3.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] message = "hello world welcome,how are you".getBytes();
        for (byte b : message) {
            byteBuffer.put(b);
        }

        byteBuffer.flip();

        outputStreamChannel.write(byteBuffer);

        outputStream.close();
    }
}
