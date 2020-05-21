package com.janita.java.base.thinkinjava._15_genericity.troubles;

import java.util.List;

/**
 * 类说明：UseList
 *
 * @author zhucj
 * @since 20200528
 */
public class UseList<W, T> {

    void f(List<T> v) {

    }

    //该方法与上面的方法无法共存
    //    void f(List<T> v) {}
}

class UseList2<W, T> {

    void f(List<T> v) {

    }

    void f2(List<T> v) {

    }
}
