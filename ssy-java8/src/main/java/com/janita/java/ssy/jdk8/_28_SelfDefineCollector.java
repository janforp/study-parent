package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * _28_SelfDefineCollector
 *
 * @author zhucj
 * @since 20200917
 */
public class _28_SelfDefineCollector {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome", "hello");
        Set<String> stringSet = list.stream().collect(new MySetCollector<String>());
        System.out.println(stringSet);
    }
}

class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        //返回新的结果容器
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        //第一个参数就是结果容器，第二个参数就是每个元素
        //return (Set<T> set, T t) -> set.add(t);使用下面的写法是一样的效果
        return Set::add;
    }

    /**
     * 负责将并行流，不同线程执行的结果合并
     *
     * @return
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("accumulator combiner!");
        return (Set<T> set1, Set<T> set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    /**
     * 最后一步，执行完返回结果
     *
     * @return
     */
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("accumulator finisher!");
        //return set -> set;
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