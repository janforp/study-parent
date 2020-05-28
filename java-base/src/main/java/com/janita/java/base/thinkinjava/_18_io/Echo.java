package com.janita.java.base.thinkinjava._18_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 类说明：Echo
 *
 * @author zhucj
 * @since 20200528
 */
public class Echo {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader standardIn = new BufferedReader(inputStreamReader);

        String s;
        //readLine() 方法是阻塞的，直到读到东西之后才返回
        while ((s = standardIn.readLine()) != null && s.length() != 0) {
            System.out.println(s);
        }
    }
}
