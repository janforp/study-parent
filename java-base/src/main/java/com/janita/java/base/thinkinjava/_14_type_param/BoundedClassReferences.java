package com.janita.java.base.thinkinjava._14_type_param;

/**
 * 类说明：BoundedClassReferences
 *
 * @author zhucj
 * @since 20200528
 */
public class BoundedClassReferences {

    public static void main(String[] args) {

        Class<? extends Number> c = int.class;
        c = double.class;
        c = Number.class;
    }
}
