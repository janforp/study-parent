package com.janita.java.ssy.jdk8;

import lombok.Data;

import java.util.List;
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
        //TODO 要求把所有Item中的SonItem都放到一个集合中
        List<Item> items = Item.get();
        System.out.println(items);
    }

}

@Data
class Item {

    private int id;

    private String desc;

    private List<SonItem> sonItemList;

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
class SonItem {

    private String sonId;

    private String sonName;
}
