package com.pingan.tsf.tcc.demo.bank.transfer;

import com.tencent.tsf.transaction.tcc.annotation.boot.EnableTcc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableTcc
public class BankTransferApp {
    public static void main(String[] args) {
        SpringApplication.run(BankTransferApp.class);
    }
}
