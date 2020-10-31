package com.segmentfault.deep.in.java.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 通配符类型
 *
 * @author zhucj
 * @since 20200917
 */
public class GenericWildcardsTypeDemo {

    public static void number() {
        List<Number> numbers = new ArrayList<>();
        numbers.add((byte) 1);
        numbers.add((short) 2);
        numbers.add(3);
        numbers.add((long) 4);

        List<Byte> bytes = Collections.singletonList((byte) 5);
        List<Short> shorts = Collections.singletonList((short) 6);
        List<Integer> integers = Collections.singletonList(7);

        numbers.addAll(bytes);
        numbers.addAll(shorts);
        numbers.addAll(integers);

        //输出的对象，需要是更为抽象的类型，如此处的 Number
        //输入对象，可以是具体类型，如short,int,byte等
        forEachObject(numbers, System.out::println);
    }

    public static void main(String[] args) {
        number();
    }

    public static void forEach(List<? extends CharSequence> list, Consumer<? extends CharSequence> consumer) {
        for (Object obj : list) {
            //报错
            //consumer.accept(obj);
        }
    }

    public static void forEachObject(Iterable<? extends Number> list, Consumer<Object> consumer) {
        for (Object obj : list) {
            consumer.accept(obj);
        }
    }

    /**
     * PECS stands for producer-extends,consumer-super
     *
     * @param producer
     * @param consumer
     */
    private static void pecs(List<? extends Number> producer, List<? super Number> consumer) {
        for (Number number : producer) {
            //消费生产者的数据或者读取数据的时候
            System.out.println(number);
        }

        //数据送给消费者或者操作数据
        consumer.add(1);
        consumer.add((byte) 1);
        consumer.add((short) 1);
    }
}
