package com.jy73.basic.dto;

import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.entity.Nutrient;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private List<Nutrient> nutrient;

    float totalCalories;

    float totalFat;

    float totalCarbohydrate;

    float totalProtein;

    private List<Long> removeList;
}
