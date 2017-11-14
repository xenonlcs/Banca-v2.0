package com.banca.conti;

import com.banca.accountable.Accountable;

public interface ContoInt {
    boolean addAccountable(Accountable acc);
    boolean fineMese();
    boolean operazione(double amount);
    String getIban();
    double getSaldo();

}
