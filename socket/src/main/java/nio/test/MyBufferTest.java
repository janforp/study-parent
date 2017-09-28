package nio.test;

import java.nio.IntBuffer;

/**
 * Created by Janita on 2017/9/28 0028-下午 1:55
 * 该类是：
 */
public class MyBufferTest {

    public static void main(String[] args) {
        intBufferPos();

        intBufferWrap();

        intBufferOther();
    }
    public static void intBufferPos() {
        IntBuffer buffer = IntBuffer.allocate(10);
        buffer.put(13);// position位置：0 - > 1
        buffer.put(21);// position位置：1 - > 2
        buffer.put(35);// position位置：2 - > 3
        //把位置复位为0，也就是position位置：3 - > 0
        buffer.flip();
        System.out.println("使用flip复位：" + buffer);
        System.out.println("容量为: " + buffer.capacity());	//容量一旦初始化后不允许改变（warp方法包裹数组除外）
        System.out.println("限制为: " + buffer.limit());		//由于只装载了三个元素,所以可读取或者操作的元素为3 则limit=3

        System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("get(index)方法，position位置不改变：" + buffer);
        buffer.put(1, 4);
        System.out.println("put(index, change)方法，position位置不变：" + buffer);

        for (int i = 0; i < buffer.limit(); i++) {
            //调用get方法会使其缓冲区位置（position）向后递增一位
            System.out.print(buffer.get() + "\t");
        }
        System.out.println("buf对象遍历之后为: " + buffer);
    }

    private static void intBufferWrap() {
        //  wrap方法会包裹一个数组: 一般这种用法不会先初始化缓存对象的长度，因为没有意义，最后还会被wrap所包裹的数组覆盖掉。
        //  并且wrap方法修改缓冲区对象的时候，数组本身也会跟着发生变化。
        int[] arr = new int[]{1,2,5};
        IntBuffer buf1 = IntBuffer.wrap(arr);
        System.out.println(buf1);
        IntBuffer buf2 = IntBuffer.wrap(arr, 0 , 2);
        //这样使用表示容量为数组arr的长度，但是可操作的元素只有实际进入缓存区的元素长度
        System.out.println(buf2);
    }

    private static void intBufferOther() {
        IntBuffer buf1 = IntBuffer.allocate(10);
        int[] arr = new int[]{1,2,5};
        buf1.put(arr);
        System.out.println(buf1);
        //一种复制方法
        IntBuffer buf3 = buf1.duplicate();
        System.out.println(buf3);

        //设置buf1的位置属性
        buf1.position(1);
        buf1.flip();
        System.out.println(buf1);
        System.out.println("可读数据为：" + buf1.remaining());
        int[] arr2 = new int[buf1.remaining()];
        //将缓冲区数据放入arr2数组中去
        buf1.get(arr2);
        for(int i : arr2){
            System.out.print(Integer.toString(i) + ",");
        }
    }
}