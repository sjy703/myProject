package com.jy73.basic.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        errorCode.setMessage(msg);
    }

    public CustomException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

}
