package com.janita.java.base.thinkinjava._17_container;

import com.janita.java.base.thinkinjava._17_container.countries.Countries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 类说明：Enumerations
 *
 * @author zhucj
 * @since 20200528
 */
public class Enumerations {

    public static void main(String[] args) {
        Vector<String> v = new Vector<String>(Countries.names(10));
        Enumeration<String> e = v.elements();
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + ", ");
        }
        // Produce an Enumeration from a Collection:
        e = Collections.enumeration(new ArrayList<String>());
    }
} /* Output:
ALGERIA, ANGOLA, BENIN, BOTSWANA, BULGARIA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE, CENTRAL AFRICAN REPUBLIC,
*///:~
