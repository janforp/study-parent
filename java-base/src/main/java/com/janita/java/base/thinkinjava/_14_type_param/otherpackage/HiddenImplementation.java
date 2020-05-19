package com.janita.java.base.thinkinjava._14_type_param.otherpackage;

import com.janita.java.base.thinkinjava._14_type_param.A;
import com.janita.java.base.thinkinjava._14_type_param.HiddenC;

import java.lang.reflect.Method;

/**
 * 类说明：HiddenImplementation
 *
 * @author zhucj
 * @since 20200528
 */
public class HiddenImplementation {

    public static void main(String[] args) throws Exception {
        //外界只能使用A,而不是使用A的实现C
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Compile error: cannot find symbol 'C':
        /* if(a instanceof C) {
          C c = (C)a;
          c.g();
        } */
        // Oops! Reflection still allows us to call g():
        callHiddenMethod(a, "g");
        // And even methods that are less accessible!(甚至是难以获得的方法)
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }

    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }

}
