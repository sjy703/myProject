package com.jy73.basic.repository.mealPlan;

import com.jy73.basic.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUserIdAndCreateDateBetween(String userId, LocalDateTime start, LocalDateTime end);

    List<MealPlan> findByUserIdAndCreateDateBetweenAndMealCategory(String userId, LocalDateTime start, LocalDateTime end, MealPlan.MealCategory category);

    Optional<MealPlan> findById(long id);
}
