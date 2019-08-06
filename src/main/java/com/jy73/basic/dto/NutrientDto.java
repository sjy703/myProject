package com.jy73.basic.dto;

import com.jy73.basic.entity.MealPlan;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutrientDto {

    private long mealPlanId;

    private String foodName;

    private float calorie;

    private float carbohydrate;

    private float protein;

    private float fat;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate createDate;
}
