package com.janita.netcode.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class BioMysqlClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9098);
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        socket.getOutputStream().write(next.getBytes(StandardCharsets.UTF_8));
        socket.close();
    }
}
