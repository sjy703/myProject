package com.jy73.basic.service;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.entity.Account;
import com.jy73.basic.repository.UserAccountRepository;
import com.jy73.basic.vo.UserAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void createAccount(Account user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userAccountRepository.save(user);
    }

    public UserAccountVo getUserInfoByPhoneNumber(UserAccountDto dto) throws Exception {
        Optional<Account> optionalAccount = userAccountRepository.findByPhoneNumber(dto.getPhoneNumber());
        Account account = optionalAccount.orElseThrow(() ->new Exception("존재하지 않는 전화번호 입니다."));
        return UserAccountVo.builder().name(account.getName()).phoneNumber(account.getPhoneNumber()).userId(account.getUserId()).build();
    }

    public UserAccountVo getUserInfoById(UserAccountDto dto) throws Exception {
        Optional<Account> optionalUser = userAccountRepository.findByUserId(dto.getUserId());
        Account account = optionalUser.orElseThrow(() ->new UsernameNotFoundException(dto.getUserId()));
        return UserAccountVo.builder().name(account.getName()).phoneNumber(account.getPhoneNumber()).userId(account.getUserId()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Account> optionalUser = userAccountRepository.findByUserId(userId);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(userId));
    }
}
