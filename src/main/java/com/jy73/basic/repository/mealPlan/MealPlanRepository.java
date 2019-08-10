package com.jy73.basic.repository.mealPlan;

import com.jy73.basic.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUserIdAndCreateDate(String userId, LocalDate createDate);

    List<MealPlan> findByUserIdAndCreateDateBetween(String userId, LocalDate start, LocalDate end);

    Optional<MealPlan> findById(long id);
}
