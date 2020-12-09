package com.pingan.tsf.tcc.demo.bank.transfer.controller;

import com.pingan.tsf.tcc.demo.bank.transfer.api.BankTransferPara;
import com.pingan.tsf.tcc.demo.bank.transfer.api.BankTransferService;
import com.pingan.tsf.tcc.demo.bank.transfer.service.TransactionManage;
import com.tencent.tsf.transaction.tcc.TransactionCancelledException;
import com.tencent.tsf.transaction.tcc.TransactionTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class BankTransferController implements BankTransferService {

    @Autowired
    private TransactionManage transactionManage;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public String tranferFromBan1ToBank2(@RequestBody BankTransferPara bankTransferPara) {
        try {
            transactionManage.startTranferFromBan1ToBank2(bankTransferPara);
        } catch (TransactionCancelledException e) {
            System.out.println("TransactionCancelledException:" + e.getMessage());
            return "转账失败，回退.";
            //return "request canceled.";
        } catch (TransactionTimeoutException e) {
            System.out.println("TransactionTimeoutException:" + e.getMessage());
            return "转账超时，回退";
            //return "request timeout.";
        } catch (Throwable e) {
            System.out.println("error!" +  e.fillInStackTrace());
            e.printStackTrace();
            return "转账异常，回退.";
            //return "request exception.";
        }

        //return "request success.";
        return "转账成功.";
    }

    @Override
    public String tranferFromBan2ToBank1(@RequestBody BankTransferPara bankTransferPara) {
        ReturnBody returnBody = new ReturnBody();

        try {
            transactionManage.startTranferFromBan2ToBank1(bankTransferPara);
        } catch (TransactionCancelledException e) {
            System.out.println("TransactionCancelledException:" + e.getMessage());
            // return "request canceled.";
            return "转账失败，回退.";
        } catch (TransactionTimeoutException e) {
            System.out.println("TransactionTimeoutException:" + e.getMessage());
            //return "request timeout.";
            return "转账超时，回退.";
        } catch (Throwable e) {
            System.out.println("error!" +  e.fillInStackTrace());
            e.printStackTrace();
            return "转账异常，回退.";
            // return "request exception.";
        }

        // return "request success.";
        return "转账成功.";
    }

    private static class ReturnBody {
        int code;
        String message;
        List<Object> list;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Object> getList() {
            return list;
        }

        public void setList(List<Object> list) {
            this.list = list;
        }
    }
}
