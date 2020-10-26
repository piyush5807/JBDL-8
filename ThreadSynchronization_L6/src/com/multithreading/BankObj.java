package com.multithreading;

public class BankObj {

    private int accountNo;
    private int accountBal;

    public int getAccountNo() {
        return accountNo;
    }

    public BankObj(int accountNo, int accountBal) {
        this.accountNo = accountNo;
        this.accountBal = accountBal;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getAccountBal() {
        return accountBal;
    }

    public void setAccountBal(int accountBal) {
        this.accountBal = accountBal;
    }

    @Override
    public String toString() {
        return "BankObj{" +
                "accountNo=" + accountNo +
                ", accountBal=" + accountBal +
                '}';
    }
}
