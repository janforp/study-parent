package com.janita.java.base.proxy.aop4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * JdkDynamicProxy
 *
 * @author zhucj
 * @since 20210225
 */
public class JdkDynamicProxy implements InvocationHandler {

    //被代理对象
    private Object target;

    //方法拦截器列表
    private List<MyMethodInterceptor> interceptorList = new ArrayList<>();

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    public void addInterceptor(MyMethodInterceptor interceptor) {
        interceptorList.add(interceptor);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
