package com.geektime.week05.spring;

import com.geektime.week05.spring.bean.CollageClass;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class SpringLoad3 {

    @Resource
    private CollageClass collageClass;

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(SpringLoad3.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);


        String location = "classpath:/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean 定义加载的数量：" + beanDefinitionsCount);

        applicationContext.refresh();

        SpringLoad3 springLoad3 = applicationContext.getBean(SpringLoad3.class);
        System.out.println(springLoad3.collageClass);

    }
}
