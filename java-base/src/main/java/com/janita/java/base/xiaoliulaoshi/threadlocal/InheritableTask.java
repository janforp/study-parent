package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 1、定义一个InheritableTask抽象类，这个类实现了Runaable接口，并定义了一个{@link InheritableTask#runTask()}runTask抽象方法，
 * 当开发者需要面对线程池获取InheritableThreadLocal值的场景时，提交的任务对象，只需要继承InheritableTask类，实现runTask方法即可。
 *
 * 2、在创建任务类时，也就是在InheritableTask构造函数中，通过反射获取到提交任务的业务线程的inheritableThreadLocals属性，然后复制一份，
 * 暂存到当前task的inheritableThreadLocalsObj属性中。
 *
 * 3、线程池线程在执行该任务时，其实就是去调用其run()方法，在执行run方法时，先将暂存的inheritableThreadLocalsObj属性赋值给当前执行任务的线程，
 * 这样这个线程就可以得到提交任务的那个业务线程的inheritableThreadLocals属性值了。然后再去执行runTask(),就是真正的业务逻辑。
 * 最后，finally清理掉执行当前业务的线程的inheritableThreadLocals属性。
 *
 * @author zhucj
 * @since 20210729
 */
@SuppressWarnings("all")
public abstract class InheritableTask implements Runnable {

    /**
     * 在创建任务类时，也就是在InheritableTask构造函数中，
     * 通过反射获取到提交任务的业务线程的inheritableThreadLocals属性，然后复制一份，
     * 暂存到当前task的inheritableThreadLocalsObj属性中。
     */
    private Object inheritableThreadLocalsObj;

    public InheritableTask() {
        try {
            // 获取业务线程的中的inheritableThreadLocals属性值
            Thread currentThread = Thread.currentThread();
            Field inheritableThreadLocalsField = Thread.class.getDeclaredField("inheritableThreadLocals");
            inheritableThreadLocalsField.setAccessible(true);
            // 得到当前线程中的inheritableThreadLocals属性值
            Object threadLocalMapObj = inheritableThreadLocalsField.get(currentThread);
            if (threadLocalMapObj != null) {
                // 调用ThreadLocal中的createInheritedMap方法，重新复制一个新的inheritableThreadLocals值
                Class threadLocalMapClazz = inheritableThreadLocalsField.getType();
                Method method = ThreadLocal.class.getDeclaredMethod("createInheritedMap", threadLocalMapClazz);
                method.setAccessible(true);
                // 创建一个新的ThreadLocalMap类型的inheritableThreadLocals
                Object newThreadLocalMap = method.invoke(ThreadLocal.class, threadLocalMapObj);
                // 将这个值暂存下来
                inheritableThreadLocalsObj = newThreadLocalMap;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 搞个代理方法，这个方法中处理业务逻辑
     */
    public abstract void runTask();

    @Override
    public void run() {
        // 此处得到的是当前处理该业务的线程，也就是线程池中的线程
        Thread currentThread = Thread.currentThread();
        Field field = null;
        try {
            field = Thread.class.getDeclaredField("inheritableThreadLocals");
            field.setAccessible(true);
            // 将暂存的值，赋值给currentThread
            if (inheritableThreadLocalsObj != null && field != null) {
                field.set(currentThread, inheritableThreadLocalsObj);
                inheritableThreadLocalsObj = null;
            }
            // 执行任务
            runTask();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            // 最后将线程中的InheritableThreadLocals设置为null
            try {
                field.set(currentThread, null);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}