package com.jy73.basic.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException {

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException() {
        super();
    }
}
