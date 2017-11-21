package com.banca.conti;

import com.banca.accountable.Accountable;
import com.banca.exceptions.OperationNotAllowed;
import com.banca.exceptions.WebException;

public interface ContoInt {
    void addAccountable(Accountable acc) throws OperationNotAllowed;
    void fineMese() throws OperationNotAllowed, WebException;
    void operazione(double amount) throws OperationNotAllowed, WebException;
    String getIban();
    double getSaldo();

}
