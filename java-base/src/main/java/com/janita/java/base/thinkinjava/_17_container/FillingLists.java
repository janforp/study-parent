package com.janita.java.base.thinkinjava._17_container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类说明：FillingLists
 *
 * @author zhucj
 * @since 20200528
 */
class StringAddress {

    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    public String toString() {
        return super.toString() + " " + s;
    }
}

public class FillingLists {

    public static void main(String[] args) {
        List<StringAddress> addressList = Collections.nCopies(4, new StringAddress("Hello"));
        List<StringAddress> list = new ArrayList<StringAddress>(addressList);
        System.out.println(list);
        Collections.fill(list, new StringAddress("World!"));
        System.out.println(list);
    }
}
