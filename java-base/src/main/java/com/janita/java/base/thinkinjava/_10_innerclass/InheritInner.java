package com.janita.java.base.thinkinjava._10_innerclass;

import com.janita.java.base.thinkinjava._10_innerclass.WithInner.Inner;

/**
 * 类说明：继承内部类
 *
 * @author zhucj
 * @since 20200423
 */
public class InheritInner extends Inner {

    /**
     * 无法使用无参数构造器 哦
     *
     * @param withInner
     */
    InheritInner(WithInner withInner) {
        withInner.super();
    }

    public static void main(String[] args) {
        WithInner withInner = new WithInner();
        InheritInner inheritInner = new InheritInner(withInner);
    }
}

class WithInner {

    class Inner {

    }
}
