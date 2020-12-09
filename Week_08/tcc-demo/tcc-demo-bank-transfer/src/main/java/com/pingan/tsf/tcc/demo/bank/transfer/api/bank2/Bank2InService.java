package com.pingan.tsf.tcc.demo.bank.transfer.api.bank2;

import com.tencent.tsf.transaction.tcc.TransactionType;
import com.tencent.tsf.transaction.tcc.TsfTcc;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bank2Service")
@RequestMapping(value = "/bank2/in")
public interface Bank2InService {
    @TsfTcc(serviceName = "bank2Service", type = TransactionType.BRANCH, confirmMethodName = "inConfirm", cancelMethodName = "inCancel")
    @RequestMapping(value = "/try", method = RequestMethod.POST)
    public void inTry(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable;

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public boolean inConfirm(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable;

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public boolean inCancel(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank2Para bank2Para) throws Throwable;
}
