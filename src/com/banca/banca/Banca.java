package com.banca.banca;

import com.banca.accountable.Accountable;
import com.banca.conti.*;
import com.banca.exceptions.InvalidIbanException;
import com.banca.exceptions.OperationNotAllowed;
import com.banca.exceptions.WebException;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.HashMap;


public class Banca {
    String nome;
    HashMap<String, ContoInt> conti;


    public Banca(String nome){
        this.nome = nome;
        conti = new HashMap<>();

    }

    public Conto aggiungiConto(String cf, ContoType type){
        Conto conto;

        if(type == ContoType.CORRENTE){
            conto = new ContoCorrente(cf, nome+conti.size());
            conti.put(nome+conti.size(), conto);
        } else if (type == ContoType.WEB){
            conto = new ContoWeb(cf, nome+conti.size());
            conti.put(nome+conti.size(), conto);
        } else if (type == ContoType.DEPOSITO){
            conto = new ContoDeposito(cf, nome+conti.size());
            conti.put(nome+conti.size(), conto);
        } else {
            conto = null;
        }

        return conto;
    }

    private ContoInt getConto(String iban) throws InvalidIbanException{
        if(conti.containsKey(iban)) return conti.get(iban);
        else throw new InvalidIbanException("Conto non esistente");
    }

    public void operazione(String iban, double amount) throws OperationNotAllowed, WebException, InvalidIbanException{
        ContoInt c = getConto(iban);
        c.operazione(amount);

    }

    public void addAccountable(String iban, Accountable acc) throws OperationNotAllowed, InvalidIbanException {
        ContoInt c = getConto(iban);
        c.addAccountable(acc);
    }

    public boolean login(String iban, String password) throws InvalidIbanException, WebException{
        ContoInt c = getConto(iban);
        if(c instanceof  ContoWeb){
            ContoWeb cw = (ContoWeb) c;
            return cw.login(password);
        }
        return false;

    }

    public void fineMese() throws OperationNotAllowed, WebException{
        for (ContoInt conto:conti.values()) {
            conto.fineMese();
        }
    }

    public void changePassword(String iban, String oldPassword, String newPassword)throws InvalidIbanException{
        ContoInt c = getConto(iban);
        if(c instanceof  ContoWeb){
            ContoWeb cw = (ContoWeb) c;
            cw.setPassword(oldPassword, newPassword);
        }

    }

    public String toString() {
        String s = "\nBanca " + nome + "\n";
        s += "------------\n";
        s += (conti==null?0:conti.size()) + " conti attivi\n";
        s += "------------\n";
        for (ContoInt c:conti.values()) {
            s += c + "\n";
        }
        s += "------------\n";
        return s + "\n";
    }

}
