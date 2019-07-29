package com.jy73.basic.repository;

import com.jy73.basic.dto.UserAccountDto;

import javax.transaction.Transactional;

public interface CustomUserAccountRepository {
    @Transactional
    void updateUserAccount(UserAccountDto dto);
}