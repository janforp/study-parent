package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * _15_Stream
 * 一个源，0个或者多个中间操作，一个终止操作
 *
 * @author zhucj
 * @since 20200723
 */
public class _15_Stream {
}

class StreamTest1 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("1", "2", "3");
        String[] strings = {"1", "2", "3"};
        Stream<String> strings1 = Stream.of(strings);
        Stream<String> stream1 = Arrays.stream(strings);
        List<String> list = Arrays.asList(strings);
        Stream<String> stringStream = list.stream();
    }
}

class StreamTest2 {

    public static void main(String[] args) {
        IntStream.of(new int[]{5, 6, 7}).forEach(System.out::println);
        System.out.println("---------");
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("---------");
        IntStream.rangeClosed(3, 8).forEach(System.out::println);

    }
}

class StreamTest3 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        //X2,然后累加
        Integer sum = list.stream()
                .map(i -> 2 * i) //中间操作，惰性求职
                .reduce(0, Integer::sum);//终止操作，及早求职
        System.out.println(sum);
        System.out.println(list);
    }
}
