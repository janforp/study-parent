package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * _16_Stream
 *
 * @author zhucj
 * @since 20200723
 */
public class _16_Stream {
}

class StreamTest4 {

    public static void main(String[] args) {
        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");

            //TODO ？》》？这个 length是个啥？？
            String[] strings = stringStream.toArray(length -> new String[length]);
            Arrays.asList(strings).forEach(System.out::println);
        }

        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            List<String> stringList = stringStream.collect(Collectors.toList());
            stringList.forEach(System.out::println);
        }

        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            List<String> stringList = stringStream.collect(Collectors.toList());
            stringList.forEach(System.out::println);
        }
    }
}
