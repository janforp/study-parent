package com.janita.netcode.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 类说明：2个点阻塞，需要多线程去支持并发，但是会造成服务器资源浪费，为了解决这些问题，就有了nio
 *
 * @author zhucj
 * @since 20200423
 */
public class BioMysqlServer {

    static byte[] bs = new byte[1024];

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9098);
        while (true) {
            System.out.println("wait conn");
            //阻塞
            Socket client = server.accept();
            System.out.println("conn success");

            System.out.println("wait data");
            InputStream inputStream = client.getInputStream();
            //阻塞
            int read = inputStream.read(bs);
            System.out.println(new String(bs, StandardCharsets.UTF_8));
        }
    }
}
