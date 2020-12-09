package com.pingan.tsf.tcc.demo.bank.transfer.api.bank2;

import java.io.Serializable;

public class Bank2Para implements Serializable {
    /**
     * 账号ID
     */
    private String accountId;

    /**
     * 金额
     */
    private double amount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bank2Para{" +
                "accountId='" + accountId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
