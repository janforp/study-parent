package com.janita.java.base.thinkinjava._14_type_param;

import static com.janita.java.base.thinkinjava._15_genericity.util.Print.print;

/**
 * 类说明：InterfaceViolation
 *
 * @author zhucj
 * @since 20200528
 */
class C implements A {

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
        print("private C.v()");
    }
}

class B implements A {

    @Override
    public void f() {

    }

    public void g() {

    }

    public static void main(String[] args) {

        String excelName = "FAN PAO-CHIN";
        String dbName = "FAN  PAO-CHIN";
        System.out.println(excelName.equals(dbName));
    }
}

public class InterfaceViolation {

    public static void main(String[] args) {

        A a = new B();
        a.f();
        //        a.g();
        System.out.println(a.getClass().getName());
        //你只想用户使用a,但是用户通过类型转换使用了B，这样你不得不把B也当作公共的API去维护，但是一开始我只是想让大家用A
        //使用包访问权限可以解决这个问题
        B b = (B) a;
        b.g();
    }
}
