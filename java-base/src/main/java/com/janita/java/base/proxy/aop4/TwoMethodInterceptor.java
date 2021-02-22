package com.janita.java.base.proxy.aop4;

/**
 * TwoMethodInterceptor
 *
 * @author zhucj
 * @since 20210225
 */
public class TwoMethodInterceptor implements MyMethodInterceptor{

    @Override
    public Object invoke(MyMethodInvocation myMethodInvocation) {
        System.out.println("拦截器2开始");
        Object proceed = myMethodInvocation.proceed();
        System.out.println("拦截器2完成");
        return proceed;
    }
}