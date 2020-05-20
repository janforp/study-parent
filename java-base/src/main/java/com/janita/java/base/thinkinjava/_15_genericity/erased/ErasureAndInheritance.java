package com.janita.java.base.thinkinjava._15_genericity.erased;

/**
 * 类说明：ErasureAndInheritance
 *
 * @author zhucj
 * @since 20200528
 */
public class ErasureAndInheritance {

    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.get();
        System.out.println(obj.getClass());
        // Warning here!
        d2.set(obj);
    }
}

class Derived1<T> extends GenericBase<T> {

}

class Derived2 extends GenericBase {

}

// class Derived3 extends GenericBase<?> {}
// Strange error:
//   unexpected type found : ?
//   required: class or interface without bounds

class GenericBase<T> {

    private T element;

    public void set(T arg) {
        arg = element;
    }

    public T get() {
        return element;
    }
}
