package com.shengsiyuan.netty.netty_49_zerocopy.tradition;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类说明：TraditionServer
 *
 * @author zhucj
 * @since 20200423
 */
public class TraditionServer {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            handle(socket);
        }
    }

    private static void handle(Socket socket) throws IOException {
        //装饰
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        byte[] byteArray = new byte[4096];
        while (true) {
            //返回实际读到的字节数
            //This method blocks until input data is available, end of file is
            //detected, or an exception is thrown
            int readCount = dataInputStream.read(byteArray, 0, byteArray.length);
            if (-1 == readCount) {
                break;
            }
        }
    }
}
