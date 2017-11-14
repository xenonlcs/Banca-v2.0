package com.banca.conti;

public class ContoDeposito extends Conto {
    public ContoDeposito(String cf, String iban) {
        super(cf, iban);
    }

    @Override
    public boolean fineMese() {
        return false;
    }

    @Override
    public boolean operazione(double amount) {
        if(amount > 0){
            saldo += amount;
            return true;
        }else{
            System.out.println("Non e' possibile effettuare un prelievo su un conto deposito");
            return false;
        }
    }
}
