package com.janita.java.base.thinkinjava._17_container.test;

/**
 * 类说明：TestParam
 *
 * @author zhucj
 * @since 20200528
 */
public class TestParam {

    /**+
     * 容器中的元素数量
     */
    public final int size;

    /**
     * 迭代的次数
     */
    public final int loops;

    public TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }

    // Create an array of TestParam from a varargs sequence:
    public static TestParam[] array(int... values) {
        int size = values.length / 2;
        TestParam[] result = new TestParam[size];
        int n = 0;
        for (int i = 0; i < size; i++) {
            result[i] = new TestParam(values[n++], values[n++]);
        }
        return result;
    }

    // Convert a String array to a TestParam array:
    public static TestParam[] array(String[] values) {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.decode(values[i]);
        }
        return array(vals);
    }
}
