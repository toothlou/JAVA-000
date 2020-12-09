package com.pingan.tsf.tcc.demo.bank.transfer.service;

import com.pingan.tsf.tcc.demo.bank.transfer.api.BankTransferPara;
import com.pingan.tsf.tcc.demo.bank.transfer.api.bank1.Bank1InService;
import com.pingan.tsf.tcc.demo.bank.transfer.api.bank1.Bank1OutService;
import com.pingan.tsf.tcc.demo.bank.transfer.api.bank1.Bank1Para;
import com.pingan.tsf.tcc.demo.bank.transfer.api.bank2.Bank2InService;
import com.pingan.tsf.tcc.demo.bank.transfer.api.bank2.Bank2OutService;
import com.pingan.tsf.tcc.demo.bank.transfer.api.bank2.Bank2Para;
import com.tencent.tsf.transaction.tcc.TransactionType;
import com.tencent.tsf.transaction.tcc.TsfTcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TransactionManage {

    private static final AtomicInteger SEQ_GENERATER = new AtomicInteger();

    @Autowired
    private Bank1InService bank1InService;

    @Autowired
    private Bank1OutService bank1OutService;

    @Autowired
    private Bank2InService bank2InService;

    @Autowired
    private Bank2OutService bank2OutService;

    @TsfTcc(serviceName = "bankTransferService", type = TransactionType.ROOT, timeout_ms = 60000)
    public String startTranferFromBan1ToBank2(BankTransferPara bankTransferPara) throws Throwable {
        Bank1Para bank1Para = new Bank1Para();
        Bank2Para bank2Para = new Bank2Para();

        bank1Para.setAccountId(bankTransferPara.getAccountIdFrom());
        bank1Para.setAmount(bankTransferPara.getAmount());

        bank2Para.setAccountId(bankTransferPara.getAccountIdTo());
        bank2Para.setAmount(bankTransferPara.getAmount());

        bank2InService.inTry("", 0, bank2Para);
        bank1OutService.outTry("", 0, bank1Para);

        return "success";
    }

    @TsfTcc(serviceName = "bankTransferService", type = TransactionType.ROOT, timeout_ms = 60000)
    public String startTranferFromBan2ToBank1(BankTransferPara bankTransferPara) throws Throwable {
        Bank1Para bank1Para = new Bank1Para();
        Bank2Para bank2Para = new Bank2Para();

        bank2Para.setAccountId(bankTransferPara.getAccountIdFrom());
        bank2Para.setAmount(bankTransferPara.getAmount());

        bank1Para.setAccountId(bankTransferPara.getAccountIdTo());
        bank1Para.setAmount(bankTransferPara.getAmount());

        bank2OutService.outTry("", 0, bank2Para);
        bank1InService.inTry("", 0, bank1Para);

        return "success";
    }
}
