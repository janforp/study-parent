package com.janita.java.base.thinkinjava._15_genericity.wildcards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类说明：UnboundedWildcards1
 *
 * @author zhucj
 * @since 20200528
 */
public class UnboundedWildcards1 {

    static List list1;

    static List<?> list2;

    static List<? extends Object> list3;

    static void assign1(List list) {
        list1 = list;
        list2 = list;
        list3 = list; // Warning: unchecked conversion
        // Found: List, Required: List<? extends Object>
    }

    static void assign2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assign3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    public static void main(String[] args) {
        assign1(new ArrayList());
        assign2(new ArrayList());
        assign3(new ArrayList()); // Warning:
        // Unchecked conversion. Found: ArrayList
        // Required: List<? extends Object>
        assign1(new ArrayList<String>());
        assign2(new ArrayList<String>());
        assign3(new ArrayList<String>());
        // Both forms are acceptable as List<?>:
        List<?> wildList = new ArrayList();
        wildList = new ArrayList<String>();
        assign1(wildList);
        assign2(wildList);
        assign3(wildList);

        List<?> list = new ArrayList<>();
        //        list.add("");
        Object o = list.get(0);
    }
}

class UnboundedWildcards2 {

    static Map map1;

    static Map<?,?> map2;

    static Map<String, ?> map3;

    static void assign1(Map map) {
        map1 = map;
    }

    static void assign2(Map<?,?> map) {
        map2 = map;
    }

    static void assign3(Map<String, ?> map) {
        map3 = map;
    }

    public static void main(String[] args) {
        assign1(new HashMap());
        assign2(new HashMap<>());
        assign3(new HashMap<>());

        assign1(new HashMap<String,Integer>());
        assign2(new HashMap<String,Integer>());
        assign3(new HashMap<String,Integer>());
    }
}
