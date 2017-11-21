package com.banca.exceptions;

public class OperationNotAllowed extends Throwable{
    private String s;

    public OperationNotAllowed(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
