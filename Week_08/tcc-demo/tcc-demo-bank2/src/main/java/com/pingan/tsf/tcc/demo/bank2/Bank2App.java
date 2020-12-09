package com.pingan.tsf.tcc.demo.bank2;

import com.tencent.tsf.transaction.tcc.annotation.boot.EnableTcc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication

//@EnableFeignClients
//@EnableTcc

public class Bank2App {
    public static void main(String[] args) {
        SpringApplication.run(Bank2App.class, args);
    }
}
