package com.janita.java.base.unsafe;

/**
 * 类说明：UnsafeThreadTest
 *
 * @author zhucj
 * @since 20200423
 */
public class UnsafeThreadTest {

    //取消阻塞线程
    public native void unpark(Object thread);

    //阻塞线程
    public native void park(boolean isAbsolute, long time);

    //获得对象锁（可重入锁）
    @Deprecated
    public native void monitorEnter(Object o);

    //释放对象锁
    @Deprecated
    public native void monitorExit(Object o);

    //尝试获取对象锁
    @Deprecated
    public native boolean tryMonitorEnter(Object o);
}
