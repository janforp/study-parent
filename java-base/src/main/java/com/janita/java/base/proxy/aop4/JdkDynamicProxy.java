package com.janita.java.base.proxy.aop4;

import com.janita.java.base.proxy.aop3.TargetMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * JdkDynamicProxy
 *
 * @author zhucj
 * @since 20210225
 */
public class JdkDynamicProxy implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object target;

    /**
     * 拦截器列表，这些拦截器就是为了增强 被代理对象的
     */
    private List<MyMethodInterceptor> interceptorList;

    /**
     * 创建JdkDynamicProxy,用来创建代理对象,以及添加拦截器
     *
     * @param target 被代理对象
     * @param interceptorList 拦截器列表，这些拦截器就是为了增强 被代理对象的
     */
    public JdkDynamicProxy(Object target, List<MyMethodInterceptor> interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //封装被代理对象的方法
        TargetMethod targetMethod = new TargetMethod(target, method, args);

        //拦截器驱动
        MyMethodInvocation invocation = new MyMethodInvocationImpl(targetMethod, interceptorList);

        //先这些拦截器，再调用被代理对象的方法
        return invocation.proceed();
    }

    /**
     * 返回代理之后的对象
     *
     * @return 代理之后的对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//类加载起
                target.getClass().getInterfaces(), //代理类需要实现的接口集合
                this); // 代理类虽然全部实现类接口方法，但是接口方法要依靠InvocationHandler去处理
    }
}
