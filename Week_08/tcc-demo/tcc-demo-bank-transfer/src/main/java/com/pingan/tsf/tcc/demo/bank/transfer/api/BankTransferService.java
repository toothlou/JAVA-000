package com.pingan.tsf.tcc.demo.bank.transfer.api;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableFeignClients
@RequestMapping
public interface BankTransferService {
    @RequestMapping(value = "/banktransfer/transferFromBan1ToBank2", method = RequestMethod.POST)
    public String tranferFromBan1ToBank2(BankTransferPara bankTransferPara);

    @RequestMapping(value = "tranferFromBan2ToBank1", method = RequestMethod.POST)
    public String tranferFromBan2ToBank1(BankTransferPara bankTransferPara);
}
