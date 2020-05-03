package com.janita.java.base.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 类说明：Test
 *
 * @author zhucj
 * @since 20200423
 */
public class Test extends Thread {

    private final TestDo testDo;

    private final String key;

    private final String value;

    public Test(String key, String key2, String value) {
        this.testDo = TestDo.getInstance();
		/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key + key2;
/*		a = "1"+"";
		b = "1"+""
*/
        this.value = value;
    }

    public static void main(String[] args) throws InterruptedException {
        Test a = new Test("1", "", "1");
        Test b = new Test("1", "", "2");
        Test c = new Test("3", "", "3");
        Test d = new Test("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    @Override
    public void run() {
        testDo.doSome(key, value);
    }
}

class TestDo {

    private TestDo() {
    }

    private static TestDo _instance = new TestDo();

    public static TestDo getInstance() {
        return _instance;
    }

    private final CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<>();

    //如果使用 arrayList，则 doSome 中可能会报错，因为4个线程同时进来该单例的方法，在迭代的过程中，有其他的线程进行修改，则会报错
    //private final List<Object> keys = new ArrayList<>();

    public void doSome(Object key, String value) {
        Object o = key;
        if (!keys.contains(o)) {
            keys.add(o);
        } else {
            for (Iterator<Object> iter = keys.iterator(); iter.hasNext(); ) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object oo = iter.next();
                //保证同步的是同一个对象
                if (oo.equals(o)) {
                    o = oo;
                    break;
                }
            }
        }
        synchronized (o)
        // 以大括号内的是需要局部同步的代码，不能改动!
        {
            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":" + (System.currentTimeMillis() / 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

