package com.jy73.basic.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealPlanDto {
    private long id;

    private String title;

    private String remark;
}
