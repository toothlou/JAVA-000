package com.pingan.tsf.tcc.demo.bank2.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/bank2/out")
public interface Bank2OutService {
    @RequestMapping(value = "/try", method = RequestMethod.POST)
    public void outTry(String txId, long branchId, Bank2Para bank2Para) throws Throwable;

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public boolean outConfirm(String txId, long branchId, Bank2Para bank2Para) throws Throwable;

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public boolean outCancel(String txId, long branchId, Bank2Para bank2Para) throws Throwable;
}
