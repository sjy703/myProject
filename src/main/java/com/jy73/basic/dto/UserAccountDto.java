package com.jy73.basic.dto;

import lombok.*;
import org.springframework.stereotype.Component;

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
}
