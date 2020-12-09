package com.pingan.tsf.tcc.demo.bank1.controller;

import com.pingan.tsf.tcc.demo.bank1.api.Bank1InService;
import com.pingan.tsf.tcc.demo.bank1.api.Bank1Para;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Bank1InServiceController implements Bank1InService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void inTry(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable {
        System.out.printf("bank1 in try: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank1Para.toString());

        String sql = "select available_amount from account_info where account_id = ?";


        throw new RuntimeException("测试bank1 in try 时出错！");
    }

    @Override
    @Transactional
    public boolean inConfirm(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable {
        System.out.printf("bank1 in confirm: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank1Para.toString());
        return true;
    }

    @Override
    @Transactional
    public boolean inCancel(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable {
        System.out.printf("bank1 in cancel: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank1Para.toString());
        return true;
    }
}
