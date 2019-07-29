package com.jy73.basic.repository;

import com.jy73.basic.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<Account, Long>, CustomUserAccountRepository {
    Optional<Account> findByUserId(String userId);

    Optional<Account> findByPhoneNumber(String phoneNumber);

}
