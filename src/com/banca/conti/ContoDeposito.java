package com.banca.conti;

import com.banca.accountable.Accountable;
import com.banca.accountable.AccountableAbs;
import com.banca.accountable.AccountableComparator;
import com.banca.accountable.AccountableType;
import com.banca.exceptions.OperationNotAllowed;

import java.util.Collections;

public class ContoDeposito extends Conto {
    public ContoDeposito(String cf, String iban) {
        super(cf, iban);
    }

    @Override
    public void fineMese() throws OperationNotAllowed {
        Collections.sort(accountables, new AccountableComparator());
        Collections.reverse(accountables);

        for(Accountable acc: accountables){
            operazione(acc.getAmount());
        }

    }

    public void addAccountable(Accountable acc) throws OperationNotAllowed{
        if(acc.getType() == AccountableType.ACCREDITO)accountables.add(acc);
        else throw new OperationNotAllowed("Non si possono assegnare addebiti ad un conto deposito");

    }

    @Override
    public void operazione(double amount) throws OperationNotAllowed{
        if(amount > 0){
            saldo += amount;

        }else{
            throw new OperationNotAllowed("Non e' possibile effettuare un prelievo su un conto deposito");

        }
    }
}
