package com.janita.java.ssy.jdk8;

/**
 * Student
 *
 * @author zhucj
 * @since 20200623
 */
public class Student2 {

    private String name;

    private int score;

    public Student2(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int compareByScore(Student2 s1, Student2 s2) {
        return s1.score - s2.score;
    }

    public static int compareByName(Student2 s1, Student2 s2) {
        return s1.name.compareTo(s2.name);
    }

    public int compareByScore2(Student2 student2) {
        return this.score - student2.score;
    }

    public int compareByName2(Student2 student2) {
        return this.name.compareToIgnoreCase(student2.name);
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}