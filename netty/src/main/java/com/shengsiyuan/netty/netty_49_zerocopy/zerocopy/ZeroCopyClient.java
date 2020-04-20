package com.shengsiyuan.netty.netty_49_zerocopy.zerocopy;

import com.shengsiyuan.netty.netty_49_zerocopy.tradition.TraditionClient;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 类说明：ZeroCopyClient
 *
 * @author zhucj
 * @since 20200423
 */
public class ZeroCopyClient {

    /**
     * 发生总字节数：470389270, 耗时：491
     */
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        FileChannel fileChannel = new FileInputStream(TraditionClient.FILE).getChannel();

        long start = System.currentTimeMillis();

        //This method is potentially much more efficient than a simple loop
        //that reads from this channel and writes to the target channel.  Many
        //operating systems can transfer bytes directly from the filesystem cache
        //to the target channel without actually copying them.
        long totalCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发生总字节数：" + totalCount + ", 耗时：" + (System.currentTimeMillis() - start));
        fileChannel.close();
    }
}
