package com.jy73.basic.dto;

import com.jy73.basic.entity.Account;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {

    private String userId;

    private String name;

    private String phoneNumber;

    private String password;

    private int height;

    private int weight;

    private int age;

    private Account.Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
}