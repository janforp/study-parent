package com.janita.java.base.thinkinjava._19_enum;

/**
 * UpcastEnum
 *
 * @author zhucj
 * @since 20200528
 */
enum Search {
    HITHER,
    YON
}

public class UpcastEnum {

    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum<Search> e = Search.HITHER; // Upcast
        // e.values(); // No values() in Enum
        Search[] enumConstants = ((Class<Search>) e.getClass()).getEnumConstants();
        for (Enum<Search> en : enumConstants) {
            System.out.println(en);
        }
    }
} /* Output:
HITHER
YON
*///:~

class NonEnum {

    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;
        try {
            for (Object en : intClass.getEnumConstants()) {
                System.out.println(en);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
} /* Output:
java.lang.NullPointerException
*///:~
