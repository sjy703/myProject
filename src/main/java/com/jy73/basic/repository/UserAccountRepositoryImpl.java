package com.jy73.basic.repository;

import com.jy73.basic.dto.UserAccountDto;
import com.jy73.basic.entity.Account;
import com.jy73.basic.entity.QAccount;
import com.querydsl.core.dml.UpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Repository
public class UserAccountRepositoryImpl extends QuerydslRepositorySupport implements CustomUserAccountRepository {


    QAccount account = QAccount.account;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserAccountRepositoryImpl() {
        super(Account.class);
    }

    @Override
    public void updateUserAccount(UserAccountDto dto) {
        UpdateClause updateClause = update(account).where(account.userId.eq(dto.getUserId()));
        if (!StringUtils.isEmpty(dto.getName())) {
            updateClause.set(account.name, dto.getName());
        }
        if (!StringUtils.isEmpty(dto.getPhoneNumber())) {
            updateClause.set(account.phoneNumber, dto.getPhoneNumber());
        }
        if (!StringUtils.isEmpty(dto.getPassword())) {
            String password = passwordEncoder.encode(dto.getPassword());
            updateClause.set(account.password, password);
        }
        if (dto.getHeight() > 0) {
            updateClause.set(account.height, dto.getHeight());
        }
        if (dto.getWeight() > 0) {
            updateClause.set(account.weight, dto.getWeight());
        }
        if (dto.getAge() > 0) {
            updateClause.set(account.age, dto.getAge());
        }
        if (!StringUtils.isEmpty(dto.getGender())) {
            updateClause.set(account.gender, dto.getGender());
        }
        updateClause.execute();
    }
}
