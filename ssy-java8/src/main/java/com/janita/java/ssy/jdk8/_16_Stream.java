package com.janita.java.ssy.jdk8;

import java.util.ArrayList;
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
        System.out.println("*************1");
        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            //TODO ？》》？这个 length是个啥？？
            String[] strings = stringStream.toArray(length -> new String[length]);
            Arrays.asList(strings).forEach(System.out::println);

            stringStream = Stream.of("hello", "world", "hello world");
            strings = stringStream.toArray(String[]::new);//构造方法引用
            Arrays.asList(strings).forEach(System.out::println);
        }
        System.out.println("*************2");

        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            List<String> stringList = stringStream.collect(Collectors.toList());
            stringList.forEach(System.out::println);
        }
        System.out.println("*************3");

        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            List<String> stringList = stringStream.collect(
                    () -> new ArrayList<>(),
                    (theList, item) -> theList.add(item),
                    (theList1, theList2) -> theList1.addAll(theList2));
            stringList.forEach(System.out::println);
        }
        System.out.println("*************4");

        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            List<String> stringList = stringStream.collect(
                    ArrayList::new,
                    ArrayList::add,
                    ArrayList::addAll);
            stringList.forEach(System.out::println);
        }

        System.out.println("*************5");
        {
            Stream<String> stringStream = Stream.of("hello", "world", "hello world");
            String concat = stringStream.collect(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append)
                    .toString();
            System.out.println(concat);
        }
    }
}
