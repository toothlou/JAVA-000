package com.geektime.week05.spring.bean;

import java.util.List;

public class CollageClass {
    private String className;

    private List<Student> studentList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Class{" +
                "className='" + className + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
