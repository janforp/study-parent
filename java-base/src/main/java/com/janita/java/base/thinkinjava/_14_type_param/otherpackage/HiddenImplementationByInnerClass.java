package com.janita.java.base.thinkinjava._14_type_param.otherpackage;

import com.janita.java.base.thinkinjava._14_type_param.A;
import com.janita.java.base.thinkinjava._14_type_param.InnerA;

/**
 * 类说明：HiddenImplementationByInnerClass
 *
 * @author zhucj
 * @since 20200528
 */
public class HiddenImplementationByInnerClass {

    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Reflection still gets into the private class:
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
