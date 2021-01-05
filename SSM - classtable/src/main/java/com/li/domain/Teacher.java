package com.li.domain;

import java.util.Arrays;

public class Teacher {
    private String name;
    private String[] grades;
    private String[] subject;

    public Teacher(String name, String[] grades, String[] subject) {
        this.name = name;
        this.grades = grades;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", grades=" + Arrays.toString(grades) +
                ", subject=" + Arrays.toString(subject) +
                '}';
    }
}
