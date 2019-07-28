package com.jy73.basic.service;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.entity.Account;
import com.jy73.basic.repository.UserAccountRepository;
import com.jy73.basic.security.JwtTokenAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtTokenAuthProvider jwtTokenAuthProvider;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    // 인증 방식이 좀 안좋다 바꾸자... 사용자 정보를 애시당초에 화면에서 인코딩해서 가져오도록 수정...
    public String login(UserAccountDto dto) throws Exception {
        Account account = userAccountRepository.findByUserId(dto.getUserId()).orElseThrow(() -> new LoginException());
        if (!passwordEncoder.matches(dto.getPassword(), account.getPassword())) {
            throw new LoginException();
        }
        return jwtTokenAuthProvider.createToken(account.getUserId(), account.getRoles(), account.getName());
    }

    @Transactional
    public void singUp(UserAccountDto dto)  {
        userAccountRepository.save(Account.builder().userId(dto.getUserId())
        .name(dto.getName()).password(passwordEncoder.encode(dto.getPassword()))
        .phoneNumber(dto.getPhoneNumber()).roles(Collections.singletonList("ROLE_USER"))
        .build());
    }


}
