package com.jy73.basic.exception;

public class TokenException extends RuntimeException{

    public TokenException(String msg) {
        super(msg);
    }

    public TokenException() {
        super();
    }
}
