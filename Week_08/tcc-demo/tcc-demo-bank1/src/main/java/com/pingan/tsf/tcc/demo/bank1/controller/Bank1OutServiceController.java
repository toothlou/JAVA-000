package com.pingan.tsf.tcc.demo.bank1.controller;

import com.pingan.tsf.tcc.demo.bank1.api.Bank1OutService;
import com.pingan.tsf.tcc.demo.bank1.api.Bank1Para;
import com.pingan.tsf.tcc.demo.bank1.exception.Bank1UnavailableException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class Bank1OutServiceController implements Bank1OutService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void outTry(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable {
        System.out.printf("bank1 out try: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank1Para.toString());

        String sql = "select available_amount from account_info where account_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, bank1Para.getAccountId());

        if (rows.size() == 0) {
            throw new Bank1UnavailableException("没有账号id为"+ bank1Para.getAccountId() + "的记录。");
        }

        double amount = (double)rows.get(0).get("available_amount");
        if (amount < bank1Para.getAmount()) {
            throw new Bank1UnavailableException(bank1Para.getAccountId() + "的账户余额不足。");
        }

        sql = "update account_info set "
                + " available_amount= available_amount - " + bank1Para.getAmount()
                + ", frozen_amount = frozen_amount + " + bank1Para.getAmount()
                + " where account_id = ?";

        int affectedRows = jdbcTemplate.update(sql, bank1Para.getAccountId());

        if (affectedRows == 0) {
            throw new  Bank1UnavailableException("更新账户时出错。");
        }

        sql = "insert into account_change_detail (account_id, change_amount, change_type, change_status, change_no) " +
                " values ( ?, ? , 2, 0, ?)";
        affectedRows = jdbcTemplate.update(sql, bank1Para.getAccountId(), bank1Para.getAmount(), txId);
        if (affectedRows == 0) {
            throw new  Bank1UnavailableException("添加账号变更表时出错。");
        }
    }

    @Override
    @Transactional
    public boolean outConfirm(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable {

        System.out.printf("bank1 out confirm: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank1Para.toString());

        String sql = "select change_no from account_change_detail where change_no = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, txId);

        if (rows.size() == 0) {
            throw new Bank1UnavailableException("confirm变更表中找不到变更号"+ txId + "的记录。");
        }

        sql = "update account_info set "
                + " frozen_amount = frozen_amount - " + bank1Para.getAmount()
                + " where account_id = ?";

        int affectedRows = jdbcTemplate.update(sql, bank1Para.getAccountId());

        if (affectedRows == 0) {
            throw new  Bank1UnavailableException("confirm更新账户时出错。");
        }

        sql = "update account_change_detail set change_status = 1 where change_no = ?";
        affectedRows = jdbcTemplate.update(sql, txId);

        if (affectedRows == 0) {
            throw new  Bank1UnavailableException("confirm更新账户变更表时出错。");
        }

        return true;
    }

    @Override
    @Transactional
    public boolean outCancel(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable {

        System.out.printf("bank1 out cancel: txId:%s, branchId:%s, bank1Para:%s\n" ,txId, branchId, bank1Para.toString());

        String sql = "select change_no from account_change_detail where change_no = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, txId);

        if (rows.size() == 0) {
            // 自身try没有成功，直接返回true
            return true;
        }

        sql = "update account_info set "
                + " available_amount= available_amount + " + bank1Para.getAmount()
                + ", frozen_amount = frozen_amount - " + bank1Para.getAmount()
                + " where account_id = ?";

        int affectedRows = jdbcTemplate.update(sql, bank1Para.getAccountId());

        if (affectedRows == 0) {
            throw new  Bank1UnavailableException("cancal更新账户时出错。");
        }

        sql = "update account_change_detail set change_status = 2 where change_no = ?";
        affectedRows = jdbcTemplate.update(sql, txId);

        if (affectedRows == 0) {
            throw new  Bank1UnavailableException("cancel更新账户变更表时出错。");
        }

        return true;
    }
}
