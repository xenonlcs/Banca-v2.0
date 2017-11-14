package com.banca.conti;

import com.banca.accountable.Accountable;

import java.util.ArrayList;

public abstract class Conto implements ContoInt{
    String cf;
    String iban;
    double saldo;
    ArrayList<Accountable> accountables;

    public Conto(String cf, String iban){
        accountables = new ArrayList<>();
        saldo = 0;
        this.cf = cf;
        this.iban = iban;
    }

    public boolean addAccountable(Accountable acc){
        accountables.add(acc);
        return true;
    }

    public String getIban(){
        return iban;
    }

    public double getSaldo(){
        return saldo;
    }


    public abstract boolean fineMese();
}
