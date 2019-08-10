package com.jy73.basic.vo;

import com.jy73.basic.entity.MealPlan;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealPlanSummaryVo {

    List<MealPlan> mealPlanList;

    float totalCalorie;

    float totalCarbohydrate;

    float totalProtein;

    float totalFat;
}
