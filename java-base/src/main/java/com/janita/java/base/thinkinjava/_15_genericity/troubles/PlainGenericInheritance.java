package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：PlainGenericInheritance
 *
 * @author zhucj
 * @since 20200528
 */
class GenericSetter<T> {

    void set(T arg) {
        System.out.println("GenericSetter.set(T)");
    }
}

class DerivedGS extends GenericSetter<Base> {

    void set(Derived derived) {
        System.out.println("DerivedGS.set(Derived)");
    }
}

public class PlainGenericInheritance {

    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS derivedGS = new DerivedGS();
        derivedGS.set(derived);
        derivedGS.set(base);
    }
}
