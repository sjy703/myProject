package com.jy73.basic.advice;

import com.jy73.basic.exception.CustomException;
import com.jy73.basic.exception.TokenException;
import com.jy73.basic.response.CommonResult;
import com.jy73.basic.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;


    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult unAuthorizedTokenException(LoginException e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            return responseService.getFailResult(messageSource.getMessage("exception.token.unAuthorized.msg", null, LocaleContextHolder.getLocale()));
        } else {
            return responseService.getFailResult(e.getMessage());
        }

    }


    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult loginException(LoginException e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            return responseService.getFailResult(messageSource.getMessage("exception.login.msg", null, LocaleContextHolder.getLocale()));
        } else {
            return responseService.getFailResult(e.getMessage());
        }
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult customException(CustomException e) {
        return responseService.getFailResult(e.getMessage());
    }
}
