package com.janita.java.base.thinkinjava._14_type_param;

import static com.janita.java.base.thinkinjava._15_genericity.util.Print.print;

/**
 * 类说明：InnerA
 *
 * @author zhucj
 * @since 20200528
 */
public class InnerA {

    /**
     * 事实证明私有的内部类对反射也没有隐私
     */
    private static class C implements A {

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
    }

    public static A makeA() {
        return new C();
    }
}
