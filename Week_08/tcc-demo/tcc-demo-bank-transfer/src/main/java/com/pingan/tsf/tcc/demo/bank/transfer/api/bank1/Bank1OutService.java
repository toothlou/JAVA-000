package com.pingan.tsf.tcc.demo.bank.transfer.api.bank1;

import com.tencent.tsf.transaction.tcc.TransactionType;
import com.tencent.tsf.transaction.tcc.TsfTcc;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bank1Service")
@RequestMapping(value = "/bank1/out")
public interface Bank1OutService {
    @TsfTcc(serviceName = "bank1Service", type = TransactionType.BRANCH, confirmMethodName = "outConfirm", cancelMethodName = "outCancel")
    @RequestMapping(value = "/try", method = RequestMethod.POST)
    public void outTry(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable;

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public boolean outConfirm(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable;

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public boolean outCancel(@RequestParam("txId") String txId, @RequestParam("branchId") long branchId, @RequestBody Bank1Para bank1Para) throws Throwable;
}
