package com.janita.java.base.thinkinjava._17_container;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 类说明：TypesForSets
 *
 * Set:元素根据 equals() 方法确定唯一性
 * HashSet:元素根据 hashCode() 实现快速查询
 * TreeSet:元素还必须实现 Comparable 接口实现排序功能
 * LinkedHashSet 具有 HashSet 的查询速度，并且具有顺序，元素也必须定义 hashCode() 方法
 *
 * @author zhucj
 * @since 20200528
 */

class SetTypeEquals {

    int i;

    public SetTypeEquals(int n) {
        i = n;
    }

    public boolean equals(Object o) {
        return o instanceof SetTypeEquals && (i == ((SetTypeEquals) o).i);
    }

    public String toString() {
        return Integer.toString(i);
    }
}

class HashTypeEqualsAndHasCode extends SetTypeEquals {

    public HashTypeEqualsAndHasCode(int n) {
        super(n);
    }

    public int hashCode() {
        return i;
    }
}

class TreeTypeEqualsAndComparable extends SetTypeEquals implements Comparable<TreeTypeEqualsAndComparable> {

    public TreeTypeEqualsAndComparable(int n) {
        super(n);
    }

    public int compareTo(TreeTypeEqualsAndComparable arg) {
        return (Integer.compare(arg.i, this.i));
    }
}

public class TypesForSets {

    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 10; i++) {
                //要求有一个整数类型的构造器
                Constructor<T> constructor = type.getConstructor(int.class);
                T newInstance = constructor.newInstance(i);
                set.add(newInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type); // Try to add duplicates
        fill(set, type);
        System.out.println(set);
    }

}

class TypesForSetsTest {

    public static void main(String[] args) {
        TypesForSets.test(new HashSet<HashTypeEqualsAndHasCode>(), HashTypeEqualsAndHasCode.class);
        TypesForSets.test(new LinkedHashSet<HashTypeEqualsAndHasCode>(), HashTypeEqualsAndHasCode.class);
        TypesForSets.test(new TreeSet<TreeTypeEqualsAndComparable>(), TreeTypeEqualsAndComparable.class);

        // Things that don't work:
        TypesForSets.test(new HashSet<SetTypeEquals>(), SetTypeEquals.class);//只有 equals 不够，还需要 hashCode
        TypesForSets.test(new HashSet<TreeTypeEqualsAndComparable>(), TreeTypeEqualsAndComparable.class);// 没有 hashCode
        TypesForSets.test(new LinkedHashSet<SetTypeEquals>(), SetTypeEquals.class);//没有 hashCode
        TypesForSets.test(new LinkedHashSet<TreeTypeEqualsAndComparable>(), TreeTypeEqualsAndComparable.class); //没有 hashCode
        try {
            //TreeSet 的元素需要实现比较器接口
            TypesForSets.test(new TreeSet<SetTypeEquals>(), SetTypeEquals.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            //TreeSet 的元素需要实现比较器接口
            TypesForSets.test(new TreeSet<HashTypeEqualsAndHasCode>(), HashTypeEqualsAndHasCode.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}