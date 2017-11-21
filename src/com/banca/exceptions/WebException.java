package com.banca.exceptions;

public class WebException extends Throwable{
    private String s;

    public WebException(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
