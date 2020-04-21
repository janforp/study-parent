package com.janita.design.mode.eventdrive;

/**
 * 类说明：Client
 *
 * @author zhucj
 * @since 20200423
 */
public class Client {

    public static void main(String[] args) {
        Student student1= new Student("张三");
        Student student2 = new Student("李四");
        Teacher teacher = new Teacher("王五");
        teacher.addObserver(student1);
        teacher.addObserver(student2);
        teacher.setHomework("事件机制第二天作业");
    }
}
