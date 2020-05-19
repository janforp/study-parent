package com.janita.java.base.thinkinjava._14_type_param;

import java.lang.reflect.Field;

/**
 * 类说明：ModifyingPrivateFields
 *
 * @author zhucj
 * @since 20200528
 */
class WithPrivateFinalField {

    private int i = 1;

    /**
     * 反射也修改不了这样的字段
     */
    private final String s = "I'm totally safe";

    private String s2 = "Am I safe?";

    @Override
    public String toString() {
        return "i = " + i + ", " + s + ", " + s2;
    }
}

public class ModifyingPrivateFields {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        WithPrivateFinalField pf = new WithPrivateFinalField();
        System.out.println(pf);

        Field f = pf.getClass().getDeclaredField("i");
        f.setAccessible(true);
        System.out.println("f.getInt(pf): " + f.getInt(pf));
        f.setInt(pf, 47);
        System.out.println(pf);


        f = pf.getClass().getDeclaredField("s");
        f.setAccessible(true);
        System.out.println("f.get(pf): " + f.get(pf));
        f.set(pf, "No, you're not!");
        System.out.println(pf);


        f = pf.getClass().getDeclaredField("s2");
        f.setAccessible(true);
        System.out.println("f.get(pf): " + f.get(pf));
        f.set(pf, "No, you're not!");
        System.out.println(pf);
    }
}
