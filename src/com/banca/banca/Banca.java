package com.banca.banca;

import com.banca.accountable.Accountable;
import com.banca.conti.*;

import java.util.HashMap;


public class Banca {
    String nome;
    HashMap<String, ContoInt> conti;
    //int c;

    public Banca(String nome){
        this.nome = nome;
        conti = new HashMap<>();
        //c=0;
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

        //c++;
        return conto;
    }

    public boolean operazione(String iban, double amount){
        return conti.get(iban).operazione(amount);
    }

    public boolean addAccountable(String iban, Accountable acc){
        return conti.get(iban).addAccountable(acc);
    }

    public boolean login(String iban, String password){
        ContoWeb cw = (ContoWeb) conti.get(iban);
        return cw.login(password);
    }

    public void fineMese(){
        for (ContoInt conto:conti.values()) {
            conto.fineMese();
        }
    }

    public boolean changePassword(String iban, String oldPassword, String newPassword){
        ContoWeb cw = (ContoWeb) conti.get(iban);
        return cw.setPassword(oldPassword, newPassword);
    }

}
