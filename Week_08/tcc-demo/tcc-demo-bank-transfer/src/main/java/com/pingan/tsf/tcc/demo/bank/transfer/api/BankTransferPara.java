package com.pingan.tsf.tcc.demo.bank.transfer.api;

public class BankTransferPara {

    /**
     * 转出账号ID
     */
    private String accountIdFrom;

    /**
     * 转出账号ID
     */
    private String accountIdTo;

    /**
     * 金额
     */
    private double amount;

    public String getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(String accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public String getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(String accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankTransferPara{" +
                "accountIdFrom='" + accountIdFrom + '\'' +
                ", accountIdTo='" + accountIdTo + '\'' +
                ", amount=" + amount +
                '}';
    }
}
