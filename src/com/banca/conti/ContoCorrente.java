package com.banca.conti;


import com.banca.accountable.Accountable;
import com.banca.accountable.AccountableComparator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ContoCorrente extends Conto {

    public ContoCorrente(String cf, String iban) {
        super(cf, iban);
    }

    @Override
    public boolean fineMese() {
        Collections.sort(accountables, new AccountableComparator());
        Collections.reverse(accountables);

        for(Accountable acc: accountables){
            if(!operazione(acc.getAmount())){
               return false;
            }

        }
        return true;
    }


    @Override
    public boolean operazione(double amount) {
        // amount > 0 deposito, amount < 0 prelievo

        if(amount >= 0){
            saldo += amount;
            return true;
        }else{
            if(saldo > Math.abs(amount)){
                saldo -= Math.abs(amount);
                return true;
            }else{
                return false;
            }
        }
    }


}
