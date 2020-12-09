package com.pingan.tsf.tcc.demo.bank2.controller;

import com.pingan.tsf.tcc.demo.bank2.api.Bank2OutService;
import com.pingan.tsf.tcc.demo.bank2.api.Bank2Para;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Bank2OutServiceController implements Bank2OutService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void outTry(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable {
        System.out.printf("bank2 out try: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank2Para.toString());
    }

    @Override
    @Transactional
    public boolean outConfirm(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable {
        System.out.printf("bank2 out confirm: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank2Para.toString());
        return true;
    }

    @Override
    @Transactional
    public boolean outCancel(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable {
        System.out.printf("bank2 out cancel: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank2Para.toString());
        return true;
    }
}
