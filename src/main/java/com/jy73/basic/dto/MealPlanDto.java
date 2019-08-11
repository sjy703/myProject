package com.jy73.basic.dto;

import com.jy73.basic.entity.MealPlan;
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

    private MealPlan.MealCategory mealCategory;

    private String remark;
}
