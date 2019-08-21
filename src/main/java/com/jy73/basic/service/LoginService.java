package com.jy73.basic.service;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.entity.Account;
import com.jy73.basic.exception.CustomException;
import com.jy73.basic.repository.userAccount.UserAccountRepository;
import com.jy73.basic.security.JwtTokenAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collections;
import java.util.Optional;

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
        return jwtTokenAuthProvider.createToken(account.getUserId(), account.getRoles());
    }

    @Transactional
    public void singUp(UserAccountDto dto) {
        if (userAccountRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new CustomException("이미 등록된 아이디입니다.");
        } else {
            float bmr = calculateBmr(dto.getBirthDate(), dto.getWeight(), dto.getHeight(), dto.getGender());
            userAccountRepository.save(Account.builder().userId(dto.getUserId()).password(passwordEncoder.encode(dto.getPassword())).roles(Collections.singletonList("ROLE_USER"))
                    .height(dto.getHeight()).weight(dto.getWeight()).gender(dto.getGender())
                    .birthDate(dto.getBirthDate()).bmr(bmr)
                    .build());
        }

    }

    private float calculateBmr(LocalDate birthDate, float weight, float height, Account.Gender gender) {
        int age = LocalDate.now().getYear() - birthDate.getYear();
        if (LocalDate.now().getMonth().getValue() < birthDate.getMonth().getValue() || LocalDate.now().getDayOfMonth() < birthDate.getDayOfMonth()) {
            age--;
        }
        if (gender.getGender().equalsIgnoreCase("male")) {
            return (float) (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age));
        } else {
            return (float) (447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age));
        }
    }


}
