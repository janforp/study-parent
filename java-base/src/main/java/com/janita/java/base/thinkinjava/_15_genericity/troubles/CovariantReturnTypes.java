package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：CovariantReturnTypes
 *
 * @author zhucj
 * @since 20200528
 */
public class CovariantReturnTypes {

    void test(DerivedGetter derivedGetter) {
        Derived derived = derivedGetter.get();
    }
}

class Base {

}

class Derived extends Base {

}

interface OrdinaryGetter {

    Base get();
}

interface DerivedGetter extends OrdinaryGetter {

    Derived get();
}


