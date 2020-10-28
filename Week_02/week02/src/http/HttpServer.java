package com.java.train.week02.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.java.train.week02.http.*")
public class HttpServer {

    public static void main(String[] args) {
        SpringApplication.run(HttpServer.class, args);
    }

}
