package com.janita.java.base.thinkinjava._17_container.countries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static com.janita.java.base.thinkinjava._17_container.countries.Countries.names;
import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：Countries
 *
 * @author zhucj
 * @since 20200528
 */
public class Countries {

    static Map<String, String> map = new FlyweightMap();

    static List<String> names = new ArrayList<String>(map.keySet());

    // Create a partial map of 'size' countries:
    static Map<String, String> select(final int size) {
        return new FlyweightMap() {
            public Set<Map.Entry<String, String>> entrySet() {
                return new EntrySetImpl(size);
            }
        };
    }

    public static Map<String, String> capitals() {
        // The entire map
        return map;
    }

    public static Map<String, String> capitals(int size) {
        // A partial map
        Map<String, String> selectMap = select(size);
        return selectMap;
    }

    // All the names:
    public static List<String> names() {
        return names;
    }

    // A partial list:
    public static List<String> names(int size) {
        Map<String, String> selectMap = select(size);
        Set<String> keySet = selectMap.keySet();
        return new ArrayList<String>(keySet);
    }
}

class CountriesTest {

    public static void main(String[] args) {
        Map<String, String> capitals = Countries.capitals(10);
        print(capitals);

        print(names(10));
        print(new HashMap<String, String>(Countries.capitals(3)));
        print(new LinkedHashMap<String, String>(Countries.capitals(3)));
        print(new TreeMap<String, String>(Countries.capitals(3)));
        print(new Hashtable<String, String>(Countries.capitals(3)));
        print(new HashSet<String>(names(6)));
        print(new LinkedHashSet<String>(names(6)));
        print(new TreeSet<String>(names(6)));
        print(new ArrayList<String>(names(6)));
        print(new LinkedList<String>(names(6)));

        Map<String, String> map = Countries.capitals();
        String brazil = map.get("BRAZIL");
        print(brazil);
    }
}

class Test111 {

    public static void main(String[] args) {
        FlyweightMap map = new FlyweightMap();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        System.out.println(entries);
    }
}