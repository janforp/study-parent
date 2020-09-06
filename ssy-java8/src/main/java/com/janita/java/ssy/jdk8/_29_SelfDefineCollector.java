package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * _29_SelfDefineCollector
 *
 * @author zhucj
 * @since 20200917
 */
public class _29_SelfDefineCollector {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world", "a", "b", "c", "123");
        Map<String, String> collect = list.stream().collect(new MySetCollector2());
        System.out.println(collect);

        collect = list.parallelStream().collect(new MySetCollector2_2<>());
        System.out.println(collect);
    }
}

class MySetCollector2 implements Collector<String, Map<String, String>, Map<String, String>> {

    @Override
    public Supplier<Map<String, String>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, String>, String> accumulator() {
        return (map, str) -> map.put(str, str);
    }

    @Override
    public BinaryOperator<Map<String, String>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<String, String>, Map<String, String>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("accumulator characteristics!");
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH,//如果有这个特点，则finisher不会调用
                Characteristics.UNORDERED));
    }
}

class MySetCollector2_2<T> implements Collector<T, Set<T>, Map<T, T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        //return HashSet::new;
        return () -> {
            System.out.println("----------");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        //中间容器会把处理的下一个元素添加进去
        return (set, item) -> {
            //此处可能会抛出异常java.util.ConcurrentModificationException
            System.out.println("accumulator:" + set + Thread.currentThread().getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("accumulator combiner!");
        return (set1, set2) -> {
            System.out.println("set1: " + set1);
            System.out.println("set2: " + set2);
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("accumulator finisher!");
        return set -> {
            Map<T, T> map = new TreeMap<>();
            set.forEach(str -> map.put(str, str));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("accumulator characteristics!");
        return Collections.unmodifiableSet(EnumSet.of(
                //Characteristics.IDENTITY_FINISH,//如果有这个特点，则finisher不会调用
                Characteristics.UNORDERED
                //如果添加了该特性，则多个线程操作同一个结果容器
                //Characteristics.CONCURRENT
                )//Exception in thread "main" java.util.ConcurrentModificationException: java.util.ConcurrentModificationException
        );
    }
}