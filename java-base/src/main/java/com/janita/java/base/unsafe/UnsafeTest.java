package com.janita.java.base.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：UnsafeTest
 *
 * copyMemory、freeMemory
 * copyMemory：内存数据拷贝
 *
 * freeMemory：用于释放allocateMemory和reallocateMemory申请的内存
 *
 * getLongVolatile/putLongVolatile等等方法
 * 这类方法使用volatile语义去存取数据，我的理解就是各个线程不缓存数据，直接在内存中读取数据；
 *
 * @author zhucj
 * @since 20200423
 */
public class UnsafeTest {

    /**
     * CAS
     *
     * @param updatedObject 包含要修改field的对象
     * @param offset 对象中某field的偏移量
     * @param expected 期望值
     * @param update 更新值
     * @return true | false
     */
    public final native boolean compareAndSwapObject(Object updatedObject, long offset, Object expected, Object update);

    public static void main(String[] args) throws InstantiationException, ClassNotFoundException {
        Unsafe unsafe = Constants.UNSAFE;
        Teacher teacher = assembleTeacher();
        updateTeacherName(unsafe, teacher, "朱老师");
        assert "朱老师".equals(teacher.getName());
        updateTeacherName(unsafe, teacher, "李老师");
        assert "李老师".equals(teacher.getName());

        unsafe.compareAndSwapInt(teacher, Constants.TEACHER_AGE_OFFSET, 30, 24);
        assertEqual(24, teacher.getAge());

        boolean compareAndSwapInt = unsafe.compareAndSwapInt(teacher, Constants.TEACHER_AGE_OFFSET, 22, 22);
        assertEqual(false, compareAndSwapInt);
        //修改学生
        Book book = teacher.getBook();
        book.setName("数学");
        unsafe.compareAndSwapObject(teacher, Constants.TEACHER_BOOK_OFFSET, book, book);
        assert "数学".equals(teacher.getBook().getName());

        boolean compareAndSwapObject = unsafe.compareAndSwapObject(teacher, Constants.TEACHER_BOOK_OFFSET, null, book);
        assertEqual(false, compareAndSwapObject);

        Student student = new Student();
        student.setName("高汤");
        student.setAge(18);
        teacher.getStudentList().add(student);
        unsafe.compareAndSwapObject(teacher, Constants.TEACHER_STUDENT_LIST_OFFSET, teacher.getStudentList(), teacher.getStudentList());
        assertEqual(2, teacher.getStudentList().size());

        //获取年龄
        int anInt = unsafe.getInt(teacher, Constants.TEACHER_AGE_OFFSET);
        assertEqual(24, anInt);

        unsafe.putInt(teacher, Constants.TEACHER_AGE_OFFSET, 55);
        assertEqual(55, teacher.getAge());

        book = teacher.getBook();
        book.setName("英语");
        Book bookFromM = (Book) unsafe.getObject(teacher, Constants.TEACHER_BOOK_OFFSET);
        assertEqual("英语", bookFromM.getName());

        Book pBook = new Book();
        pBook.setName("物理");
        unsafe.putObject(teacher, Constants.TEACHER_BOOK_OFFSET, pBook);
        assertEqual(teacher.getBook(), pBook);

        teacher.setFemale(true);
        boolean unsafeBoolean = unsafe.getBoolean(teacher, Constants.TEACHER_FEMALE_OFFSET);
        assertEqual(true, unsafeBoolean);

        unsafe.putBoolean(teacher, Constants.TEACHER_FEMALE_OFFSET, false);
        assertEqual(false, teacher.isFemale());

        PrivateClass privateClass = (PrivateClass) unsafe.allocateInstance(PrivateClass.class);
        privateClass.setRemark(2);
        assertEqual(2, privateClass.getRemark());

        Class<?> forName = Class.forName("com.janita.java.base.erase.PrivateClassTest");
        Object instance = unsafe.allocateInstance(forName);
        assertEqual(instance.getClass(), forName);

        int intVolatile = unsafe.getIntVolatile(teacher, Constants.TEACHER_AGE_OFFSET);
        assertEqual(55, intVolatile);

        //先返回，再添加，最后更新
        int andAddInt = unsafe.getAndAddInt(teacher, Constants.TEACHER_AGE_OFFSET, 10);
        assertEqual(55, andAddInt);
        assertEqual(65, teacher.getAge());

        //先返回，再修改
        int oldInt = unsafe.getAndSetInt(teacher, Constants.TEACHER_AGE_OFFSET, 18);
        assertEqual(65, oldInt);
        assertEqual(18, teacher.getAge());

        int addressSize = unsafe.addressSize();
        String jdkBit = System.getProperty("sun.arch.data.model");
        if ("64".equals(jdkBit)) {
            assertEqual(8, addressSize);
        }

        int pageSize = unsafe.pageSize();
        assertEqual(4096, pageSize);


    }

    private static <T> void assertEqual(T expected, T actual) {
        if (expected == actual || expected.equals(actual)) {
            return;
        }
        throw new RuntimeException("wrong, expected = " + expected + ", actual = " + actual);
    }

    private static Teacher assembleTeacher() {
        Book book = new Book();
        book.setName("语文");
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setAge(22);
        student.setName("周杰伦");
        studentList.add(student);

        Teacher teacher = new Teacher();
        teacher.setName("高老师");
        teacher.setAge(30);
        teacher.setTeacherNo("00001");
        teacher.setBook(book);
        teacher.setStudentList(studentList);
        return teacher;
    }

    private static void updateTeacherName(Unsafe unsafe, Teacher teacher, String name) {
        unsafe.compareAndSwapObject(teacher, Constants.TEACHER_NAME_OFFSET, teacher.getName(), name);
    }
}

class Constants {

    static final sun.misc.Unsafe UNSAFE;

    static final long TEACHER_FEMALE_OFFSET;

    static final long TEACHER_NAME_OFFSET;

    static final long TEACHER_AGE_OFFSET;

    static final long TEACHER_NO_OFFSET;

    static final long TEACHER_STUDENT_LIST_OFFSET;

    static final long TEACHER_BOOK_OFFSET;

    static {
        try {
            Class<?> clazz = Teacher.class;
            UNSAFE = UnsafeFactory.getUnsafe();
            assert UNSAFE != null;
            TEACHER_FEMALE_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("female"));
            TEACHER_NAME_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("name"));
            TEACHER_AGE_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("age"));
            TEACHER_NO_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("teacherNo"));
            TEACHER_BOOK_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("book"));
            TEACHER_STUDENT_LIST_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("studentList"));

        } catch (Exception e) {
            throw new Error(e);
        }
    }
}

@Data
class Teacher {

    private boolean female;

    private String name;

    private int age;

    private String teacherNo;

    private Book book;

    private List<Student> studentList;
}

@Data
class Student {

    private int age;

    private String name;
}

@Data
class Book {

    private String name;
}

@Data
class PrivateClass {

    private int remark;

    private PrivateClass() {
    }
}

class MonitorTest {

    public static void main(String[] args) throws InterruptedException {
        testNoSync();
        testWithSync();
        testWithMonitor();
    }

    private static void testWithMonitor() throws InterruptedException {
        final Test test = new Test();
        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    try {
                        test.addCountWithMonitor();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    try {
                        test.addCountWithMonitor();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread.sleep(5000);
        System.out.println(test.getCount());
    }

    private static void testWithSync() throws InterruptedException {
        final Test test = new Test();
        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    test.addCountWithSync();
                }
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    test.addCountWithSync();
                }
            }
        }.start();

        Thread.sleep(5000);
        System.out.println(test.getCount());
    }

    private static void testNoSync() throws InterruptedException {
        final Test test = new Test();
        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    test.addCountNoSync();
                }
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    test.addCountNoSync();
                }
            }
        }.start();

        Thread.sleep(5000);
        System.out.println(test.getCount());
    }
}

@Data
class Test {

    private int count = 0;

    private Object lock = new Object();

    public void addCountNoSync() {
        count ++;
    }

    public synchronized void addCountWithSync() {
        count ++;
    }

    public void addCountWithMonitor() throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        try {
            unsafe.monitorEnter(lock);
            count++;
        }finally {
            unsafe.monitorExit(lock);
        }
    }
}
