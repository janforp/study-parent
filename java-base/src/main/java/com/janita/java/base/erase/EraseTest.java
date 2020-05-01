package com.janita.java.base.erase;

import lombok.Data;

import java.util.ArrayList;

/**
 * 类说明：EraseTest
 *
 * @author zhucj
 * @since 20200423
 */
public class EraseTest {

    public static void main(String[] args) {
        testEraseOne();
    }

    private static void testEraseOne() {
        Class<? extends ArrayList> aClass = new ArrayList<String>().getClass();
        Class<? extends ArrayList> aClass1 = new ArrayList<Integer>().getClass();
        //true
        System.out.println(aClass == aClass1);
    }
}

class GenericTest<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        GenericTest<String> genericTest = new GenericTest<>();
        genericTest.set("jay@huaxiao");
        String s = genericTest.get();
        System.out.println(s);
    }
}

@Data
class PrivateClassTest {

    private String name = "默认名称";

    private PrivateClassTest() {}
}