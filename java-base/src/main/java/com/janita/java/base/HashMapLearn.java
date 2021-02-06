package com.janita.java.base;

/**
 * HashMapLearn
 *
 * @author zhucj
 * @since 20210128
 */
public class HashMapLearn {

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Returns a power of two size for the given target capacity.
     * 该方法就是把传入的cap转化为不小于cap的2的次方数
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ?
                1
                : (n >= MAXIMUM_CAPACITY) ?
                MAXIMUM_CAPACITY :
                n + 1;
    }

    /**
     * 扰动函数
     * 作用：让key的hash值的高16位也参与路由运算
     * 异或：相同则返回0，不同返回1
     *
     * 目的就是让hash更加散列
     *
     * key的hash值为h
     * h            = 0b 0010 0101 1010 1100 0011 1111 0010 1110
     *
     * h左移16位
     * (h >>> 16)   = 0b 0000 0000 0000 0000 0010 0101 1010 1100
     *
     * 进行异或运算
     * h^(h >>> 16) = 0b 0010 0101 1010 1100 0001 1010 1000 0010
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ?
                0 : //null的key则放在第一个槽位
                (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(10));//16
        System.out.println(tableSizeFor(11));//16
        System.out.println(tableSizeFor(30));//32
        System.out.println(tableSizeFor(31));//32

    }

    /**
     * @param var1 要操作的对象
     * @param var2 要操作对象中属性地址的偏移量
     * @param var4 要修改数据的期望值
     * @param var5 新值
     * @return 成功失败
     */
    public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);

    public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);

    public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
}
