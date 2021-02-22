package com.janita.java.base.proxy.aop4;

import com.janita.java.base.proxy.aop3.TargetMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        TargetMethod targetMethod = new TargetMethod(target, method, args);
        MyMethodInvocation invocation = new MyMethodInvocationImpl(targetMethod, interceptorList);
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
