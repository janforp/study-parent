package com.janita.design.mode.proxy.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200326
 */
public class WorkHandler<T> implements InvocationHandler {

    /**
     * 代理类中的真实对象
     */
    private final T obj;

    /**
     * 构造函数，给我们的真实对象赋值
     *
     * @param obj 给我们的真实对象赋值
     */
    public WorkHandler(T obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke。。。");
        Object invoke = method.invoke(obj, args);
        //在真实的对象执行之后我们可以添加自己的操作
        System.out.println("after invoke。。。");
        return invoke;
    }
}
