package com.janita.java.base.thinkinjava._14_type_param.otherpackage;

import com.janita.java.base.thinkinjava._14_type_param.A;
import com.janita.java.base.thinkinjava._14_type_param.AnonymousA;

/**
 * 类说明：AnonymousImplementation
 *
 * @author zhucj
 * @since 20200528
 */
public class AnonymousImplementation {

    public static void main(String[] args) throws Exception {
        A a = AnonymousA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Reflection still gets into the anonymous class:
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
