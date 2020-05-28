package com.janita.java.base.thinkinjava._19_enum.一些很好的用法;

import static com.janita.java.base.thinkinjava.util.Print.print;
import static com.janita.java.base.thinkinjava.util.Print.printnb;

/**
 * OverrideConstantSpecific
 *
 * @author zhucj
 * @since 20200528
 */
public enum OverrideConstantSpecific {
    NUT,
    BOLT,
    WASHER {

        @Override
        void f() {
            print("Overridden method");
        }
    };

    void f() {
        print("default behavior");
    }

    public static void main(String[] args) {
        for (OverrideConstantSpecific ocs : values()) {
            printnb(ocs + ": ");
            ocs.f();
        }
    }
} /* Output:
NUT: default behavior
BOLT: default behavior
WASHER: Overridden method
*///:~
