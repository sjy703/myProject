package com.jy73.basic.advice;

import com.jy73.basic.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    /*private final ResponseService responseService;*/
    private final MessageSource messageSource;


    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected Exception unAuthorizedTokenException(TokenException e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            String message = messageSource.getMessage("exception.token.unAuthorized.msg", null, LocaleContextHolder.getLocale());
            return new TokenException(message);
        } else {
            return e;
        }

    }


    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected Exception loginException(LoginException e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            return new LoginException(messageSource.getMessage("exception.login.msg", null, LocaleContextHolder.getLocale()));
        } else {
            return e;
        }
    }

    /*@ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Exception customException(CustomException e) {
        return responseService.getFailResult(e.getMessage());
    }*/
}
