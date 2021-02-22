package com.janita.java.base.proxy.aop4;

/**
 * OneMethodInterceptor
 *
 * @author zhucj
 * @since 20210225
 */
public class OneMethodInterceptor implements MyMethodInterceptor{

    @Override
    public Object invoke(MyMethodInvocation myMethodInvocation) {
        System.out.println("拦截器1开始");
        Object proceed = myMethodInvocation.proceed();
        System.out.println("拦截器1完成");
        return proceed;
    }
}