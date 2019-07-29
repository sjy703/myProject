package com.jy73.basic.controller;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.service.UserAccountService;
import com.jy73.basic.vo.UserAccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/find")
    public UserAccountVo find(UserAccountDto dto) throws Exception {
        if (!StringUtils.isEmpty(dto.getUserId())) {
            return userAccountService.getUserInfoById(dto);
        } else {
            return userAccountService.getUserInfoByPhoneNumber(dto);
        }

    }

    @PostMapping("/update")
    public boolean update(@RequestBody UserAccountDto dto) throws Exception {
        userAccountService.getUserInfoById(dto); // user id 있나없나 확인 없으면 exception
        userAccountService.updateUserInfo(dto);
        return true;
    }
}
