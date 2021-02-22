package com.janita.java.base.proxy.aop4;

import java.lang.reflect.InvocationTargetException;

/**
 * MyMethodInvocation
 *
 * @author zhucj
 * @since 20210225
 */
public interface MyMethodInvocation {

    //驱动拦截器练，执行增强了解，被代理方法调用
    Object proceed() throws InvocationTargetException, IllegalAccessException;
}