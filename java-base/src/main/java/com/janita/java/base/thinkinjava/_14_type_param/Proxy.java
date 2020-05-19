package com.janita.java.base.thinkinjava._14_type_param;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.janita.java.base.thinkinjava.Print.print;

/**
 * 类说明：Proxy
 *
 * @author zhucj
 * @since 20200528
 */
public class Proxy {

}

interface Interface {

    void doSomeThing();

    void doSomeThingElse(String arg);
}

class RealObject implements Interface {

    @Override
    public void doSomeThing() {
        print("doSomeThing");
    }

    @Override
    public void doSomeThingElse(String arg) {
        print("doSomeThingElse " + arg);
    }
}

class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomeThing() {
        print("SimpleProxy doSomething");
        proxied.doSomeThing();
    }

    @Override
    public void doSomeThingElse(String arg) {
        print("SimpleProxy doSomeThingElse");
        proxied.doSomeThingElse(arg);
    }
}

class SimpleProxyDemo {

    public static void consumer(Interface iface) {
        iface.doSomeThing();
        iface.doSomeThingElse("bonono");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}

class DynamicProxyHandler implements InvocationHandler {

    private Interface proxied;

    public DynamicProxyHandler(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args);
        if (args != null) {
            for(Object arg : args)
                System.out.println("  " + arg);
        }
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {

    public static void consumer(Interface iface) {
        iface.doSomeThing();
        iface.doSomeThingElse("bonono");
    }

    public static void main(String[] args) {

        RealObject realObject = new RealObject();
        consumer(realObject);


        Interface instance = (Interface)java.lang.reflect.Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[] { Interface.class },
                new DynamicProxyHandler(realObject));

        consumer(instance);
    }
}

class MethodSelector implements InvocationHandler {

    private Object proxied;

    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("interesting")) {
            print("Proxy detected the interesting method");
        }
        return method.invoke(proxied, args);
    }
}

interface SomeMethods {

    void boring1();

    void boring2();

    void interesting(String arg);

    void boring3();
}

class Implementation implements SomeMethods {

    @Override
    public void boring1() {
        print("boring1");
    }

    @Override
    public void boring2() {
        print("boring2");

    }

    @Override
    public void interesting(String arg) {

        print("interesting " + arg);
    }

    @Override
    public void boring3() {
        print("boring3");

    }
}

class SelectingMethods {

    public static void main(String[] args) {
        SomeMethods someMethods = (SomeMethods) java.lang.reflect.Proxy.newProxyInstance(SomeMethods.class.getClassLoader(), new Class[] { SomeMethods.class }, new MethodSelector(new Implementation()));
        someMethods.boring1();
        someMethods.boring2();
        someMethods.interesting("bonobo");
        someMethods.boring3();
    }
}