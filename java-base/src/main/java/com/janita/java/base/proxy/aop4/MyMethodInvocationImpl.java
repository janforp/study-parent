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

    private TargetMethod targetMethod;

    private List<MyMethodInterceptor> interceptorList;

    private int index = 0;

    public MyMethodInvocationImpl(TargetMethod targetMethod, List<MyMethodInterceptor> interceptorList) {
        this.targetMethod = targetMethod;
        this.interceptorList = interceptorList;
    }

    @Override
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        if (index == interceptorList.size()) {
            return targetMethod.getMethod().invoke(
                    targetMethod.getTarget(),
                    targetMethod.getArgs());
        }

        MyMethodInterceptor interceptor = interceptorList.get(index++);
        return interceptor.invoke(this);
    }

    public void setInterceptorList(List<MyMethodInterceptor> interceptorList) {
        this.interceptorList = interceptorList;
    }
}
