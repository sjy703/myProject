package com.jy73.basic.controller;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAccountDto dto) throws Exception {
        String token = loginService.login(dto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-AUTH-TOKEN", token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Success");
    }

    @PostMapping("/signup")
    public boolean signUp(@RequestBody UserAccountDto dto) {
        loginService.singUp(dto);
        return true;
    }
}
