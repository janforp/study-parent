package com.janita.java.ssy.jdk8;

/**
 * Employee
 *
 * @author zhucj
 * @since 20200623
 */
public class Employee {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
