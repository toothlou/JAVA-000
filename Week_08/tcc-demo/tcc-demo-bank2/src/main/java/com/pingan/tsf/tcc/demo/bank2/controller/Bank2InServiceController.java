package com.pingan.tsf.tcc.demo.bank2.controller;

import com.pingan.tsf.tcc.demo.bank2.api.Bank2InService;
import com.pingan.tsf.tcc.demo.bank2.api.Bank2Para;
import com.pingan.tsf.tcc.demo.bank2.exception.Bank2UnavailableException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class Bank2InServiceController implements Bank2InService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void inTry(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable {
        System.out.printf("bank2 in try: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank2Para.toString());

        String sql = "select available_amount from account_info where account_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, bank2Para.getAccountId());

        if (rows.size() == 0) {
            throw new Bank2UnavailableException("没有账号id为"+ bank2Para.getAccountId() + "的记录。");
        }

        sql = "update account_info set "
                + " frozen_amount = frozen_amount + " + bank2Para.getAmount()
                + " where account_id = ?";

        int affectedRows = jdbcTemplate.update(sql, bank2Para.getAccountId());

        if (affectedRows == 0) {
            throw new  Bank2UnavailableException("更新账户时出错。");
        }

        sql = "insert into account_change_detail (account_id, change_amount, change_type, change_status, change_no) " +
                " values ( ?, ? , 1, 0, ?)";
        affectedRows = jdbcTemplate.update(sql, bank2Para.getAccountId(), bank2Para.getAmount(), txId);
        if (affectedRows == 0) {
            throw new  Bank2UnavailableException("添加账号变更表时出错。");
        }

    }

    @Override
    @Transactional
    public boolean inConfirm(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable {
        System.out.printf("bank2 in confirm: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank2Para.toString());

        String sql = "select change_no from account_change_detail where change_no = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, txId);

        if (rows.size() == 0) {
            throw new Bank2UnavailableException("confirm变更表中找不到变更号"+ txId + "的记录。");
        }

        sql = "update account_info set "
                + " frozen_amount = frozen_amount - " + bank2Para.getAmount()
                + ", available_amount = available_amount + " + bank2Para.getAmount()
                + " where account_id = ?";

        int affectedRows = jdbcTemplate.update(sql, bank2Para.getAccountId());

        if (affectedRows == 0) {
            throw new  Bank2UnavailableException("confirm更新账户时出错。");
        }

        sql = "update account_change_detail set change_status = 1 where change_no = ?";
        affectedRows = jdbcTemplate.update(sql, txId);

        if (affectedRows == 0) {
            throw new  Bank2UnavailableException("confirm更新账户变更表时出错。");
        }

        return true;
    }

    @Override
    @Transactional
    public boolean inCancel(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable {
        System.out.printf("bank2 in cancel: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank2Para.toString());

        String sql = "select change_no from account_change_detail where change_no = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, txId);

        if (rows.size() == 0) {
            // 自身try没有成功，直接返回true
            return true;
        }

        sql = "update account_info set "
                + " frozen_amount = frozen_amount - " + bank2Para.getAmount()
                + " where account_id = ?";

        int affectedRows = jdbcTemplate.update(sql, bank2Para.getAccountId());

        if (affectedRows == 0) {
            throw new  Bank2UnavailableException("cancal更新账户时出错。");
        }

        sql = "update account_change_detail set change_status = 2 where change_no = ?";
        affectedRows = jdbcTemplate.update(sql, txId);

        if (affectedRows == 0) {
            throw new  Bank2UnavailableException("cancel更新账户变更表时出错。");
        }

        return true;
    }
}
