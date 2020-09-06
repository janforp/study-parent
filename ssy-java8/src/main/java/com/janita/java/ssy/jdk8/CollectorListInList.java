package com.janita.java.ssy.jdk8;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CollectorListInList
 *
 * @author zhucj
 * @since 20200917
 */
public class CollectorListInList {

    public static void main(String[] args) {
        List<Item> items = get();

        //TODO 要求把所有Item中的SonItem都放到一个集合中

        //方法1：
        List<SonItem> collect = items.stream()
                .collect(new CollectorListInListImpl());
        System.out.println(collect);

        //方法2：
        List<SonItem> sonItemList = items.stream()
                .flatMap(item -> item.getSonItemList().stream())
                .collect(Collectors.toList());
        System.out.println(sonItemList);
    }

    static List<Item> get() {
        return Stream.iterate(1, i -> i + 1).map(i -> {
            Item item = new Item();
            item.setId(i);
            item.setDesc("item: " + i);
            item.setSonItemList(Stream.iterate(1, j -> j + 1).map(j -> {
                SonItem sonItem = new SonItem();
                sonItem.setSonId(i + "-" + j);
                sonItem.setSonName("sonName:" + i + "-" + j);
                return sonItem;
            }).limit(3).collect(Collectors.toList()));
            return item;
        }).limit(4).collect(Collectors.toList());
    }
}

@Data
class Item {

    private int id;

    private String desc;

    private List<SonItem> sonItemList;
}

@Data
class SonItem {

    private String sonId;

    private String sonName;
}

class CollectorListInListImpl implements Collector<Item, List<SonItem>, List<SonItem>> {

    @Override
    public Supplier<List<SonItem>> supplier() {
        System.out.println("supplier invoked!");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<SonItem>, Item> accumulator() {
        System.out.println("accumulator invoked!");
        return (list, item) -> list.addAll(item.getSonItemList());
    }

    @Override
    public BinaryOperator<List<SonItem>> combiner() {
        System.out.println("accumulator combiner!");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<SonItem>, List<SonItem>> finisher() {
        System.out.println("accumulator finisher!");
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


