package com.banca.conti;

import com.banca.accountable.Accountable;
import com.banca.exceptions.OperationNotAllowed;
import com.banca.exceptions.WebException;

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

    public void addAccountable(Accountable acc) throws OperationNotAllowed{
        accountables.add(acc);

    }

    public String getIban(){
        return iban;
    }

    public double getSaldo(){
        return saldo;
    }


    public abstract void fineMese() throws OperationNotAllowed, WebException;

    @Override
    public String toString() {
        return "iban: " + iban + ";" + "\tintestatario: " + cf
                + ";" + "\tsaldo: " + saldo;
    }
}
