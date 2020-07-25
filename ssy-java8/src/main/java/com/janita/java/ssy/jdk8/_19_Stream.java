package com.janita.java.ssy.jdk8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * _19_Stream
 *
 * @author zhucj
 * @since 20200723
 */
public class _19_Stream {

    public static void main(String[] args) {
        List<Integer> collect = Stream.iterate(1, i -> i + 1)
                .limit(100)
                .filter(i -> {
                    System.out.println("filter1: " + i);
                    return i > 5;
                })
                .filter(i -> {
                    System.out.println("filter2: " + i);
                    return i % 2 == 0;
                }).collect(Collectors.toList());
        System.out.println(collect);
    }
}