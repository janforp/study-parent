package com.janita.datastructuresandalgorithms.book3.chapter3;

/**
 * 编码实现一种数据结构，要求
 * 1.支持栈push和pop操作以及操作findMin，findMin返回数据结构中的最小元素
 * 2.所有操作均以O(1)
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3_25_MyIntStack {

    private int[] data;//用于支持push和pop操作的数组

    private int[] minData;//用于支持findMin操作的数组，存储一个最小值序列

    private int dataIndex;//data数组的下标索引

    private int minIndex;//minData数组的下标索引

    private int min;//最小值

    private int size;//元素的个数

    //在构造函数中进行初始化
    public Exercise3_25_MyIntStack(int length) {
        data = new int[length];
        minData = new int[length];
        dataIndex = -1;
        minIndex = -1;
        min = 0;
        size = 0;
    }

    //入栈
    public void push(int x) {
        size++;
        data[++dataIndex] = x;
        if (dataIndex == 0 //如果是第一个元素，就初始化到最小元素数组
                || data[dataIndex] <= min) {//或者最新添加的元素比上一个最小元素还小，则也添加到最小元素数组，并且更新min
            min = data[dataIndex];
            minData[++minIndex] = data[dataIndex];
        }
    }

    public int pop() {//出栈,最小值发生改变
        size--;
        int a = data[dataIndex--];
        //如果出栈元素就是最小的元素，则更新最小元素的引用
        if ((a == minData[minIndex]) && dataIndex >= 0) {
            min = minData[--minIndex];
        }
        return a;
    }

    //找出数据结构中的最小值
    public int findMin() {
        return minData[minIndex];
    }

    public boolean isEmpty() {    //判断数组是否为空
        return size == 0;
    }
}