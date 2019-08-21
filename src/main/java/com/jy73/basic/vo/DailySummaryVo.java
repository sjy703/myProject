package com.jy73.basic.vo;


import com.jy73.basic.entity.DailyBodyStatus;
import com.jy73.basic.entity.MealPlan;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySummaryVo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private DailyBodyStatus dailyBodyStatus;

    private MealPlan breakfast;

    private MealPlan lunch;

    private MealPlan dinner;

    private List<MealPlan> nosh = new ArrayList<>();

    private float totalCalories;

    private float totalFat;

    private float totalCarbohydrate;

    private float totalProtein;

    private float fatPercentage;

    private float carbohydratePercentage;

    private float proteinPercentage;
}
