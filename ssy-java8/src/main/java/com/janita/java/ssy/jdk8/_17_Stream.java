package com.janita.java.ssy.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
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

        {
            List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
            list.stream().map(i -> i * i).forEach(System.out::println);
        }
        System.out.println("*****FLAT_MAP***");
        {
            List<List<Integer>> list
                    = Arrays.asList(Collections.singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
            //将 list 中的每一个元素的每个数字平方后输出
            list.stream().flatMap(theList -> theList.stream().map(i -> i * i)).forEach(System.out::println);
        }
    }
}

class StreamTest6 {

    public static void main(String[] args) {
        {
            Stream<String> generate = Stream.generate(UUID.randomUUID()::toString);
            System.out.println(generate.findFirst().get());//有风险
        }

        {
            Stream<String> generate = Stream.generate(UUID.randomUUID()::toString);
            generate.findFirst().ifPresent(System.out::println);//推荐使用方法
        }

        {
            Stream<String> generate = Stream.empty();
            generate.findFirst().ifPresent(System.out::println);
        }

        {
            //Stream.iterate(1, item -> item + 2).forEach(System.out::println);
            Stream.iterate(2, item -> item + 2).limit(6).forEach(System.out::println);
        }

        {
            Optional<Integer> sumOption = Stream.iterate(1, item -> item + 2).limit(6)
                    .filter(item -> item > 2)
                    .map(item -> item * 2)
                    .skip(2)
                    .limit(2)
                    .reduce(Integer::sum);
            sumOption.ifPresent(System.out::println);
        }

        {
            Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
            int sum = stream
                    .filter(item -> item > 2)
                    .mapToInt(item -> item * 2)
                    .skip(2)
                    .limit(2)
                    .sum();//没有元素，最多就是返回0，所以就不用Optional包装
            System.out.println(sum);

        }

        {
            Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
            OptionalInt min = stream
                    .filter(item -> item > 2)
                    .mapToInt(item -> item * 2)
                    .skip(2)
                    .limit(2)
                    .min();
            min.ifPresent(System.out::println);
        }

        {
            Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
            OptionalInt max = stream
                    .filter(item -> item > 2)
                    .mapToInt(item -> item * 2)
                    .skip(2)
                    .limit(2)
                    .max();
            max.ifPresent(System.out::println);
        }

        System.out.println("****  IntSummaryStatistics  ****");
        {
            Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
            IntSummaryStatistics statistics = stream.filter(item -> item > 2)
                    .mapToInt(item -> item * 2)
                    .skip(2)
                    .limit(2).summaryStatistics();
            System.out.println(statistics);
        }
    }
}
