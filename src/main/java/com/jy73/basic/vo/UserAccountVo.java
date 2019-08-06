package com.jy73.basic.vo;

import com.jy73.basic.entity.Account;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountVo {
    private String userId;

    private String name;

    private String phoneNumber;

    private String password;

    private int height;

    private int weight;

    private int age;

    private Account.Gender gender;

    private long bmr;

    private LocalDateTime birthDate;
}
