package com.banca.accountable;

public abstract class AccountableAbs implements Accountable {
    double amount;
    AccountableType type;

    public AccountableAbs(double amount, AccountableType type){
        this.amount = amount;
        this.type = type;
    }

    @Override
    public AccountableType getType() {
        return type;
    }

    @Override
    public double getAmount() {
        if(type == AccountableType.ACCREDITO) return amount;
        else return amount*(-1);
    }
}
