package com.geektime.week05.spring;

import com.geektime.week05.spring.bean.Student;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SpringLoad2 {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(SpringLoad2.class);
        // 启动应用上下文
        applicationContext.refresh();

        Map<String, Student> map = applicationContext.getBeansOfType(Student.class);
        for(Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        // 关闭应用上下文
        applicationContext.close();

    }

    @Bean
    public Student user() {
        Student student = new Student();
        student.setAge(15);
        student.setName("张三");
        return student;
    }

}
