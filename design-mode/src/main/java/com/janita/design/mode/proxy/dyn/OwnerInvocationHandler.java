package com.janita.design.mode.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 类说明：OwnerInvocationHandler
 *
 * @author zhucj
 * @since 20200423
 */
public class OwnerInvocationHandler implements InvocationHandler {

    PersonBean person;

    public OwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(person, args);
        } else if (method.getName().equals("setHotOrNotRating")) {
            return new IllegalAccessException();
        } else if (method.getName().startsWith("set")) {
            return method.invoke(person, args);
        }
        return null;
    }
}