package com.jy73.basic.controller;

import com.jy73.basic.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exception")
public class ExceptionController {
    @GetMapping("/token")
    public Exception tokenException() {
        throw new TokenException();
    }
}
