package com.janita.java.base.thinkinjava._15_genericity;

/**
 * 类说明：Generator
 *
 * @author zhucj
 * @since 20200528
 */
public interface Generator<T> {

    T next();
}