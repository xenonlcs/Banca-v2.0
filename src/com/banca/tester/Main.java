package com.banca.tester;

import com.banca.accountable.*;
import com.banca.banca.Banca;
import com.banca.conti.ContoType;
import com.banca.exceptions.InvalidIbanException;
import com.banca.exceptions.OperationNotAllowed;
import com.banca.exceptions.WebException;

public class Main {
    public static void main(String[] args) {
        Banca b = new Banca("UBI");

        b.aggiungiConto("AAA",ContoType.CORRENTE);
        b.aggiungiConto("BBB",ContoType.CORRENTE);
        b.aggiungiConto("CCC",ContoType.WEB);
        b.aggiungiConto("DDD",ContoType.DEPOSITO);

        try{
            b.operazione("UBI0", 1000);
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }

        try{
            b.operazione("UBI1", 2000);
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }

        try{
            b.operazione("UBI1", -30);
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }


        try{
            b.operazione("UBI2", 200); //fails since not logged in
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }


        try{
            b.login("UBI2", "changeme");
        }catch (WebException|InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.changePassword("UBI2", "changeme", "newpass");//you have to change your pass as first operation;
        }catch (InvalidIbanException e){
            e.printStackTrace();
        }
        try{

            b.changePassword("UBI2", "newpass", "test");//fails! cannot change it more than once!
        }catch (InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.login("UBI2", "newpass");//now succeeds
        }catch (WebException|InvalidIbanException e){
            e.printStackTrace();
        }



        try{
            b.operazione("UBI2", 2500);
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.operazione("UBI2", -100);
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.operazione("UBI3", 5000);
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.operazione("UBI3", -1000); //fails since it's deposit
        }catch (OperationNotAllowed|WebException|InvalidIbanException e){
            e.printStackTrace();
        }


        System.out.println(b);

        Accountable s1 = new Stipendio(1300, AccountableType.ACCREDITO);
        Accountable abb1 = new AbbonamentoPayTv(50, AccountableType.ADDEBITO);
        Accountable abb2 = new AbbonamentoPayTv(140, AccountableType.ADDEBITO);

        try{
            b.addAccountable("UBI0", abb1);
        }catch(OperationNotAllowed|InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.addAccountable("UBI2", abb2);
        }catch(OperationNotAllowed|InvalidIbanException e){
            e.printStackTrace();
        }
        try{
            b.addAccountable("UBI3", s1);
        }catch(OperationNotAllowed|InvalidIbanException e){
            e.printStackTrace();
        }



        try{
            b.fineMese();
        }catch(OperationNotAllowed|WebException e){
            e.printStackTrace();
        }

        System.out.println("FINE MESE TRIGGERED");
        System.out.println(b);
    }
}
