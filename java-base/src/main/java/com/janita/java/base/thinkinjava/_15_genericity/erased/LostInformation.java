package com.janita.java.base.thinkinjava._15_genericity.erased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类说明：LostInformation
 *
 * @author zhucj
 * @since 20200528
 */
public class LostInformation {

    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));

        Map<Frob, Fnorkle> map = new HashMap<>();
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));

        Quark<Fnorkle> quark = new Quark<>();
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));

        Particle<Long, Double> p = new Particle<>();
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
}

class Frob {

}

class Fnorkle {

}

class Quark<Q> {

}

class Particle<POSITION, MOMENTUM> {

}
