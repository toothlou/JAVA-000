package com.ljg.starter.config;

import com.ljg.starter.cls.Klass;
import com.ljg.starter.cls.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterConfiguration {

    @Bean("student123")
    public Student getStudent123() {
        Student student = new Student();
        student.setId(123);
        student.setName("KK123");
        return student;
    }

    @Bean("student100")
    public Student getStudent100() {
        Student student = new Student();
        student.setId(100);
        student.setName("KK100");
        return student;
    }

    @Bean("class1")
    public Klass getClass1() {
        Klass klass = new Klass();
//        Student student = new Student();
//        student.setId(100);
//        student.setName("KK100");
//        klass.getStudents().add(student);
        klass.getStudents().add(getStudent100());
        klass.getStudents().add(getStudent123());
        return klass;
    }
}
