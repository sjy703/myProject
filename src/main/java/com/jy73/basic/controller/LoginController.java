package com.jy73.basic.controller;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.entity.Account;
import com.jy73.basic.service.LoginService;
import com.jy73.basic.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final UserAccountService userAccountService;

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody UserAccountDto dto) throws Exception {
        String token = loginService.login(dto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-AUTH-TOKEN", token);
        Account user = userAccountService.getUserInfoById(dto);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(user);
    }

    @PostMapping("/signup")
    public boolean signUp(@RequestBody UserAccountDto dto) {
        loginService.singUp(dto);
        return true;
    }
}
