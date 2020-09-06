package com.janita.java.ssy.jdk8;

/**
 * _32_GroupingBy
 *
 * @author zhucj
 * @since 20200917
 */
public class _32_GroupingByAnd_33_PartitionBy {

}

class AutoCloseableTest implements AutoCloseable {

    private void doSomething() {
        System.out.println("close doSomething!");
    }

    @Override
    public void close() {
        System.out.println("close invoked!");
    }

    public static void main(String[] args) {
        try (AutoCloseableTest test = new AutoCloseableTest()) {
            test.doSomething();
        }
    }
}