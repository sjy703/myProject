package com.jy73.basic.controller;

import com.jy73.basic.dto.MealPlanDto;
import com.jy73.basic.dto.NutrientDto;
import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.service.MealPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public List<MealPlan> getMealPlans(MealPlan mealPlan) {
        return mealPlanService.getMealPlans(mealPlan);
    }

    @GetMapping("/plan/duration")
    public List<MealPlan> getMealPlansBetween(@RequestParam("id") String userId, @RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return mealPlanService.getMealPlansBetween(userId, start, end);
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
    public void addNutrient(@RequestBody NutrientDto dto) {
        mealPlanService.addNutrient(dto);
    }

    @DeleteMapping("/nutrient")
    public void deleteNutrient(long mealPlanId, long id) {
        mealPlanService.removeNutrient(mealPlanId, id);
    }
}
