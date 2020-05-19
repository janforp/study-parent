package com.janita.java.base.thinkinjava._14_type_param;

import java.util.Random;

/**
 * 类说明：ClassInitialization
 *
 * @author zhucj
 * @since 20200528
 */
public class ClassInitialization {

    public static Random rand = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {
        Class<Initable> initable = Initable.class;
        System.out.println("创建变量 Class<Initable> initable 之后");
        System.out.println(Initable.staticFinal);
        System.out.println(Initable.staticFinal2);
    }
}

class Initable {

    /**
     * 编译期常量，直接使用他不会导致该类初始化
     */
    static final int staticFinal = 47;

    /**
     * 非编译期常量，直接使用他会导致该类初始化
     */
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("初始化 Initabl");
    }
}

class Initable2 {

    static int staticNonFinal = 147;

    static {
        System.out.println("初始化 Initable2");
    }

    public static void main(String[] args) {
        System.out.println(Initable2.staticNonFinal);
    }
}

class Initable3 {

    static int staticNonFinal = 74;

    static {
        System.out.println("初始化 Initable3");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> initable3 = Class.forName("com.janita.java.base.thinkinjava._14_type_param.Initable3");
        System.out.println("创建变量 Class<?> initable3 之后");
        System.out.println(Initable3.staticNonFinal);
    }
}