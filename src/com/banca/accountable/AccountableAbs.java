package com.banca.accountable;

public abstract class AccountableAbs implements Accountable {
    double amount;

    public AccountableAbs(double amount){
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
