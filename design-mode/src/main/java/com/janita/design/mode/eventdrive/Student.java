package com.janita.design.mode.eventdrive;

/**
 * 类说明：Student
 *
 * @author zhucj
 * @since 20200423
 */
public class Student implements HomeworkListener {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(HomeworkEventObject o, Object arg) {
        Teacher teacher = o.getTeacher();
        System.out.printf("学生%s观察到（实际是被通知）%s布置了作业《%s》 \n", this.name, teacher.getName(), arg);
    }
}
