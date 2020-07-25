package com.janita.java.ssy.jdk8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * _18_Stream
 *
 * @author zhucj
 * @since 20200723
 */
public class _18_Stream {

    public static void main(String[] args) {
        {
            Stream<String> stringStream = Stream.of("hello", "world", "helloworld");
            stringStream.map(item -> {
                String result = item.substring(0, 1).toUpperCase() + item.substring(1);
                //不会输出
                System.out.println("test");
                return result;
            });
        }

        {
            Stream<String> stringStream = Stream.of("hello", "world", "helloworld");
            stringStream.map(item -> {
                String result = item.substring(0, 1).toUpperCase() + item.substring(1);
                System.out.println("test");
                return result;
            }).forEach(System.out::println);
        }
    }
}

class StreamTest8 {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> (i + 1) % 2)
                .distinct()//该程序永远都不会正常退出
                .limit(6)
                .forEach(System.out::println);
    }
}

class StreamTest8_2 {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> (i + 1) % 2)
                .limit(6)
                .distinct()
                .forEach(System.out::println);
    }
}