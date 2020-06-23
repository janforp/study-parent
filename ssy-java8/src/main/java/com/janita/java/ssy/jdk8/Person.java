package com.janita.java.ssy.jdk8;

/**
 * Person
 *
 * @author zhucj
 * @since 20200623
 */
public class Person {

    private String username;

    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
