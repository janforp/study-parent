package com.shengsiyuan.netty.netty_49_zerocopy.tradition;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * 类说明：TraditionClient
 *
 * @author zhucj
 * @since 20200423
 */
public class TraditionClient {

    public static final String FILE = "/Users/janita/Desktop/视频/netty教程并发编程/05_Netty执行流程分析与重要组件介绍.mp4";

    /**
     * 发生总字节数：470389270, 耗时：1867
     */
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);
        FileInputStream fileInputStream = new FileInputStream(FILE);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] byteArray = new byte[4096];
        long readCount;
        long totalCount = 0;

        long start = System.currentTimeMillis();
        while ((readCount = fileInputStream.read(byteArray)) >= 0) {
            totalCount = totalCount + readCount;
            dataOutputStream.write(byteArray);
        }
        System.out.println("发生总字节数：" + totalCount + ", 耗时：" + (System.currentTimeMillis() - start));

        dataOutputStream.close();
        socket.close();
        fileInputStream.close();
    }

}
