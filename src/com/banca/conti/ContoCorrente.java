package com.banca.conti;


import com.banca.accountable.Accountable;
import com.banca.accountable.AccountableComparator;
import com.banca.exceptions.OperationNotAllowed;
import com.banca.exceptions.WebException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ContoCorrente extends Conto {

    public ContoCorrente(String cf, String iban) {
        super(cf, iban);
    }

    @Override
    public void fineMese() throws OperationNotAllowed, WebException {
        Collections.sort(accountables, new AccountableComparator());
        Collections.reverse(accountables);

        for(Accountable acc: accountables){
            operazione(acc.getAmount());
        }

    }


    @Override
    public void operazione(double amount) throws OperationNotAllowed, WebException {
        // amount > 0 deposito, amount < 0 prelievo

        if(amount >= 0){
            saldo += amount;
        }else{
            if(saldo > Math.abs(amount)){
                saldo -= Math.abs(amount);
            }else{
                throw new OperationNotAllowed("Saldo insufficiente");
            }
        }
    }


}
