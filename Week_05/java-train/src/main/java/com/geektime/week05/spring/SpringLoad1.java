package com.geektime.week05.spring;

import com.geektime.week05.spring.bean.CollageClass;
import com.geektime.week05.spring.bean.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class SpringLoad1 {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 配置文件 ClassPath 路径
        String location = "classpath:/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean 定义加载的数量：" + beanDefinitionsCount);

        Student student1 = (Student)beanFactory.getBean("std1");
        Student student2 = (Student)beanFactory.getBean("std2");
        CollageClass class1 = (CollageClass)beanFactory.getBean("class1");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(class1);
    }

}
