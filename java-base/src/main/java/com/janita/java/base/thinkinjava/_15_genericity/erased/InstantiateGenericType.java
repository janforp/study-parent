package com.janita.java.base.thinkinjava._15_genericity.erased;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：InstantiateGenericType
 *
 * @author zhucj
 * @since 20200528
 */
class ClassAsFactory<T> {

    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            //Class 是 java 内置的 工厂
            x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

public class InstantiateGenericType {

    public static void main(String[] args) {

        ClassAsFactory<Employee> factory = new ClassAsFactory<>(Employee.class);
        print("ClassAsFactory<Employee> succeeded");

        try {
            ClassAsFactory<Integer> integerClassAsFactory = new ClassAsFactory<>(Integer.class);
        } catch (Exception e) {
            print("ClassAsFactory<Integer> failed");
        }
    }
}

class Employee {

}