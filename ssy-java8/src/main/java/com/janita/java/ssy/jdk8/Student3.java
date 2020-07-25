package com.janita.java.ssy.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Student
 *
 * @author zhucj
 * @since 20200623
 */
@Data
@AllArgsConstructor
public class Student3 {

    private String name;

    private int score;

    private int age;
}