package com.janita.java.ssy.jdk8;

/**
 * Student
 *
 * @author zhucj
 * @since 20200623
 */
public class Student {
    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name = "zhangsan";

    private int age = 20;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
