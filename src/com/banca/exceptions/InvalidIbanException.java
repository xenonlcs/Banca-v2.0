package com.banca.exceptions;

public class InvalidIbanException extends Throwable {
    private String s;

    public InvalidIbanException(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
