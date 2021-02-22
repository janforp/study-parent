package com.janita.java.base.proxy.aop4;

import com.janita.java.base.proxy.aop3.TargetMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * MyMethodInvocationImpl
 *
 * @author zhucj
 * @since 20210225
 */
public class MyMethodInvocationImpl implements MyMethodInvocation {

    /**
     * 封装了被代理方法，拦截器都执行完成之后就要调用该方法
     */
    private TargetMethod targetMethod;

    /**
     * 拦截器链表
     */
    private List<MyMethodInterceptor> interceptorList;

    /**
     * 拦截器执行的次序
     */
    private int index = 0;

    public MyMethodInvocationImpl(TargetMethod targetMethod, List<MyMethodInterceptor> interceptorList) {
        this.targetMethod = targetMethod;
        this.interceptorList = interceptorList;
    }

    @Override
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        if (index == interceptorList.size()) {
            //所有拦截器都执行完毕，则执行代理对象自己
            return targetMethod.getMethod().invoke(
                    targetMethod.getTarget(),
                    targetMethod.getArgs());
        }

        //按顺序执行拦截器
        MyMethodInterceptor interceptor = interceptorList.get(index++);
        return interceptor.invoke(this);
    }
}
