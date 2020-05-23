package com.janita.java.base.thinkinjava._14_type_param;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：AnonymousA
 *
 * @author zhucj
 * @since 20200528
 */
public class AnonymousA {

    public static A makeA() {
        return new A() {
            @Override
            public void f() {
                print("public C.f()");
            }

            public void g() {
                print("public C.g()");
            }

            void u() {
                print("package C.u()");
            }

            protected void v() {
                print("protected C.v()");
            }

            private void w() {
                print("private C.w()");
            }
        };
    }
}
