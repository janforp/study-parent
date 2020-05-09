package com.shengsiyuan.netty;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorChooserFactory;

/**
 * 类说明：OtherTest
 *
 * @author zhucj
 * @since 20200528
 */
public class OtherTest {

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(4));
        System.out.println(isPowerOfTwo(5));
        System.out.println(isPowerOfTwo(6));
        System.out.println(isPowerOfTwo(7));
        System.out.println(isPowerOfTwo(8));

    }

    private static boolean isPowerOfTwo(int val) {
        return (val & -val) == val;
    }
}

class Impl implements EventExecutorChooserFactory.EventExecutorChooser {

    @Override
    public EventExecutor next() {
        return null;
    }
}
