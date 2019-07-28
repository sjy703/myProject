package com.jy73.basic.vo;

import lombok.*;
import org.springframework.stereotype.Component;

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
}
