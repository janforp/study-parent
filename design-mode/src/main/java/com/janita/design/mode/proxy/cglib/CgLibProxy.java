package com.janita.design.mode.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 类说明：CGLibProxy
 *
 * @author zhucj
 * @since 20200423
 */
public class CgLibProxy implements MethodInterceptor {

    private static final CgLibProxy INSTANCE = new CgLibProxy();

    private CgLibProxy() {
    }

    public static CgLibProxy getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void after() {
        System.out.println("after......");
    }

    private void before() {
        System.out.println("before......");
    }
}
