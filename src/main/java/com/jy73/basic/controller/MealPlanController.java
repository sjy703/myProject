package com.jy73.basic.controller;

import com.jy73.basic.dto.MealPlanDto;
import com.jy73.basic.dto.NutrientDto;
import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.service.MealPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meal")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    @PostMapping("/plan")
    public void createMealPlan(@RequestBody MealPlan mealPlan) {
        mealPlanService.createMealPlan(mealPlan);
    }

    @GetMapping("/plan")
    public MealPlan getMealPlan(MealPlan mealPlan) {
        return mealPlanService.getMealPlan(mealPlan);
    }

    @PutMapping("/plan")
    public void updateMealPlan(MealPlanDto dto) {
        mealPlanService.updateMealPlan(dto);
    }

    @DeleteMapping("/plan")
    public void deleteMealPlan(long id) {
        mealPlanService.deleteMealPlan(id);
    }

    @PostMapping("/nutrient")
    public void addNutrient(@RequestBody NutrientDto dto)
    {
        mealPlanService.addNutrient(dto);
    }

    @DeleteMapping("/nutrient")
    public void deleteNutrient(long mealPlanId, long id)
    {
        mealPlanService.removeNutrient(mealPlanId, id);
    }
}
