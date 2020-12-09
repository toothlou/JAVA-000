package com.pingan.tsf.tcc.demo.bank1.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/bank1/out")
public interface Bank1OutService {
    @RequestMapping(value = "/try", method = RequestMethod.POST)
    public void outTry(String txId, long branchId, Bank1Para bank1Para) throws Throwable;

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public boolean outConfirm(String txId, long branchId, Bank1Para bank1Para) throws Throwable;

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public boolean outCancel(String txId, long branchId, Bank1Para bank1Para) throws Throwable;
}
