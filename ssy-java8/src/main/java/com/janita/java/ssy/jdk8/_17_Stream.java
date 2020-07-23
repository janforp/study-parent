package com.janita.java.ssy.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * _17_Stream
 *
 * @author zhucj
 * @since 20200723
 */
public class _17_Stream {

    public static void main(String[] args) {

        {
            Stream<String> stringStream = Stream.of("hello", "world", "helloworld");
            List<String> arrayList = stringStream.collect(Collectors.toCollection(ArrayList::new));
            System.out.println(arrayList.getClass());
            arrayList.forEach(System.out::println);
        }

        {
            Stream<String> stringStream = Stream.of("hello", "world", "helloworld");
            Set<String> treeSet = stringStream.collect(Collectors.toCollection(TreeSet::new));
            System.out.println(treeSet.getClass());
            treeSet.forEach(System.out::println);
        }

        {
            Stream<String> stringStream = Stream.of("hello", "world", "helloworld");
            String collect = stringStream.collect(Collectors.joining());
            System.out.println(collect);
        }
    }
}

class StreamTest5 {

    public static void main(String[] args) {
        {
            List<String> list = Arrays.asList("hello", "world", "helloworld", "test", "我们");
            list.stream().map(String::toUpperCase).forEach(System.out::println);
        }
    }
}
