package com.janita.java.ssy.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * _20_Stream
 *
 * @author zhucj
 * @since 20200723
 */
public class _20_Stream {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(50000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }

        System.out.println("开始排序");
        long startTime = System.nanoTime();
        Stream<String> stream = list.stream();
        //        list.stream().sorted().count();
        list.parallelStream().sorted().count();

        long endTime = System.nanoTime();
        long time = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(time);
    }
}

class StreamTest10 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "wolrd", "hello world");
        list.stream().filter(str -> str.length() == 5).findFirst().ifPresent(System.out::println);
        list.stream().mapToInt(String::length).filter(l -> l == 5).findFirst().ifPresent(System.out::println);

        System.out.println("短路操作");
        list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(l -> l == 5).findFirst().ifPresent(System.out::println);

    }
}

class StreamTest11 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "hello world", "hello world hello", "hello welcome");
        List<String> collect = list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
}