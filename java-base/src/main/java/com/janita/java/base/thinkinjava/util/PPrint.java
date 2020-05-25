package com.janita.java.base.thinkinjava.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 类说明：PPrint
 *
 * @author zhucj
 * @since 20200528
 */

public class PPrint {

    public static String pformat(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for (Object elem : c) {
            if (c.size() != 1) {
                result.append("\n  ");
            }
            result.append(elem);
        }
        if (c.size() != 1) {
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }

    public static void pprint(Collection<?> c) {
        System.out.println(pformat(c));
    }

    public static void pprint(Object[] c) {
        System.out.println(pformat(Arrays.asList(c)));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("12313", "213123", "dsfsdfs");
        System.out.println(pformat(list));
        pprint(new String[] { "12313", "213123", "dsfsdfs" });
        pprint(new String[] { "one element" });
    }
}
