package com.jy73.basic.repository.mealPlan;

import com.jy73.basic.entity.MealPlan;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MealPlanRepositoryImpl extends QuerydslRepositorySupport implements CustomMealPlanRepository {


    public MealPlanRepositoryImpl() {
        super(MealPlan.class);
    }

}
