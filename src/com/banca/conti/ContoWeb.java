package com.banca.conti;

import com.banca.exceptions.OperationNotAllowed;
import com.banca.exceptions.WebException;

public class ContoWeb extends ContoCorrente {

    private String password;
    private boolean loggedIn;
    private boolean firstAccess;

    public ContoWeb(String cf, String iban) {
        super(cf, iban);
        loggedIn = false;
        firstAccess = true;
        password = "changeme";
    }

    public boolean login(String password) throws WebException{
        /*if(firstAccess){
            setPassword("changeme", password);
            firstAccess = false;
        }*/
        if(this.password.equals(password)){
            loggedIn = true;
            return true;
        }else{
            throw new WebException("Password errata");
        }

    }

    public boolean logout(){
        loggedIn = false;
        return true;
    }

    public boolean setPassword(String oldPassword, String newPassword){
        if(oldPassword.equals(password)){
            password = newPassword;
            firstAccess = false;
            return true;
        }
        return false;
    }

    public void operazione(double amount) throws OperationNotAllowed, WebException{
        if(loggedIn && !firstAccess){
            super.operazione(amount);
        }else if(firstAccess){
            throw new WebException("Devi cambiare la prima password");
        }
        else{
            throw new WebException("Devi prima effettuare l'accesso");
        }

    }

}
