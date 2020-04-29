package com.janita.java.base.generics;

/**
 * 类说明：GeneratorClassTest
 *
 * @author zhucj
 * @since 20200423
 */
public class GeneratorClassTest {

    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("first", 1);
        String first = pair.getFirst();
        System.out.println(first);
        Integer second = pair.getSecond();
        System.out.println(second);

        pair.setFirst("first2");
        pair.setSecond(2);

        first = pair.getFirst();
        System.out.println(first);
        second = pair.getSecond();
        System.out.println(second);
    }
}

class Pair<K, V> {

    private K first;

    private V second;

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}
