package com.janita.java.base.thinkinjava._15_genericity.mixin;

import com.janita.java.base.thinkinjava._15_genericity.TwoTuple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static com.janita.java.base.thinkinjava._15_genericity.util.Tuple.tuple;

/**
 * 类说明：DynamicProxyMixin
 *
 * TODO 这个 动态代理有点东西
 *
 * @author zhucj
 * @since 20200528
 */
class MixinProxy implements InvocationHandler {

    Map<String, Object> delegatesByMethod = new HashMap<>();

    public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
        for (TwoTuple<Object, Class<?>> pair : pairs) {

            Object first = pair.first;

            Class<?> second = pair.second;
            Method[] methods = second.getMethods();

            for (Method method : methods) {
                String methodName = method.getName();
                // The first interface in the map
                // implements the method.
                if (!delegatesByMethod.containsKey(methodName)) {
                    delegatesByMethod.put(methodName, first);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }

    @SuppressWarnings("unchecked")
    //<Basic, Class<Basic>
    public static Object newInstance(TwoTuple<Object, Class<?>>... pairs) {
        Class[] interfaces = new Class[pairs.length];

        for (int i = 0; i < pairs.length; i++) {
            Class second = pairs[i].second;
            interfaces[i] = second;
        }

        Class<?> firstClass = pairs[0].first.getClass();
        ClassLoader classLoader = firstClass.getClassLoader();

        MixinProxy mixinProxy = new MixinProxy(pairs);

        return Proxy.newProxyInstance(classLoader, interfaces, mixinProxy);
    }
}

public class DynamicProxyMixin {

    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImp(), Basic.class),
                tuple(new TimeStampedImp(), TimeStamped.class),
                tuple(new SerialNumberedImp(), SerialNumbered.class));
        Basic b = (Basic) mixin;
        TimeStamped t = (TimeStamped) mixin;
        SerialNumbered s = (SerialNumbered) mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
    }
}