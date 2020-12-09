package com.pingan.tsf.tcc.demo.bank1.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/bank1/in")
public interface Bank1InService {
    @RequestMapping(value = "/try", method = RequestMethod.POST)
    public void inTry(String txId, long branchId, Bank1Para bank1Para) throws Throwable;

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public boolean inConfirm(String txId, long branchId, Bank1Para bank1Para) throws Throwable;

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public boolean inCancel(String txId, long branchId, Bank1Para bank1Para) throws Throwable;
}
