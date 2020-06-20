package com.janita.java.ssy.jdk8;

/**
 * FunctionInterface
 *
 * @author zhucj
 * @since 20200623
 */
public @FunctionalInterface
interface FunctionInterface {

    int doSth();

    //Object 的方法可以
    int hashCode();

    boolean equals(Object obj);

    //不能添加
    //int doElse();
}