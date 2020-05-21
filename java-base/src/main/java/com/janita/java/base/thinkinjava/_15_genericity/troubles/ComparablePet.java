package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：ComparablePet
 *
 * @author zhucj
 * @since 20200528
 */
public class ComparablePet implements Comparable<ComparablePet> {

    @Override
    public int compareTo(ComparablePet o) {
        return 0;
    }
}

//class Cat extends ComparablePet implements Comparable<Cat> {}

class Hamster extends ComparablePet implements Comparable<ComparablePet> {

    @Override
    public int compareTo(ComparablePet o) {
        return 0;
    }
}

class Gecko extends ComparablePet {

    @Override
    public int compareTo(ComparablePet o) {
        return 0;
    }
}

/**
 * 自限定，有什么意义呢
 *
 * @param <T>
 */
class SelfBounded<T extends SelfBounded<T>> {

}