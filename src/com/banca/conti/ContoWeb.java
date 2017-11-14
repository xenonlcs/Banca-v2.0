package com.banca.conti;

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

    public boolean login(String password){
        if(firstAccess){
            setPassword("changeme", password);
            firstAccess = false;
        }
        if(this.password.equals(password)){
            loggedIn = true;
            return true;
        }
        return false;
    }

    public boolean logout(){
        loggedIn = false;
        return true;
    }

    public boolean setPassword(String oldPassword, String newPassword){
        if(oldPassword.equals(password)){
            password = newPassword;
            return true;
        }
        return false;
    }

    public boolean operazione(double amount){
        if(loggedIn && !firstAccess){
            super.operazione(amount);
            return true;
        }
        return false;
    }

}
