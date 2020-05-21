package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：GenericsAndReturnTypes
 *
 * @author zhucj
 * @since 20200528
 */
interface GenericGetter<T extends GenericGetter<T>> {

    T get();
}

interface Getter extends GenericGetter<Getter> {

}

public class GenericsAndReturnTypes {

    void test(Getter getter) {

        Getter result = getter.get();
        GenericGetter genericGetter = getter.get();
    }
}

class OrdinarySetter {

    void set(Base base) {
        System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter {

    /**
     * 没有覆盖，而是重载
     *
     * @param derived
     */
    void set(Derived derived) {
        System.out.println("DerivedSetter.set(Derived)");
    }
}

class OrdinaryArguments {

    public static void main(String[] args) {

        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter derivedSetter = new DerivedSetter();

        derivedSetter.set(derived);
        derivedSetter.set(base);
    }
}

interface SelfBoundSetter<T extends SelfBoundSetter<T>> {

    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {

}

interface Setter2 extends SelfBoundSetter<Setter> {

}

interface Setter3 extends SelfBoundSetter<Setter3> {

}

class SelfBoundAndCovariantArguments {

    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
        //编译器不能识别将基类型当作参数传递给 set() 的尝试，因为没有任何方法具有这样的签名
        //实际上，这个方法已经被 Setter 覆盖了，参数必须是 Setter
        //s1.set(sbs);
    }

    void testB(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
        //编译器不能识别将基类型当作参数传递给 set() 的尝试，因为没有任何方法具有这样的签名
        //实际上，这个方法已经被 Setter 覆盖了，参数必须是 Setter
        //s1.set(sbs);
    }

    void testC(Setter s1) {
        s1.set(new Setter() {
            @Override
            public void set(Setter arg) {

            }
        });
//        s1.set(new Setter2(){
        //
        //            @Override
        //            public void set(Setter arg) {
        //
        //            }
        //        });

    }
}