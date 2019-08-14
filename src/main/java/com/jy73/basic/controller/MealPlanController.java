package com.jy73.basic.controller;

import com.jy73.basic.dto.MealPlanDto;
import com.jy73.basic.dto.NutrientDto;
import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.service.MealPlanService;
import com.jy73.basic.vo.DailySummaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<MealPlan> getMealPlans(@RequestParam("id") String userId, @RequestParam("createDate") String date, @RequestParam(value="category", required = false) MealPlan.MealCategory category) {
        LocalDate createDate = LocalDate.parse(date);
        if (category == null)
            return mealPlanService.getMealPlans(userId, createDate);
        return mealPlanService.getMealPlansByCategory(userId, createDate, category);
    }

    @GetMapping("/plan/duration")
    public List<MealPlan> getMealPlansBetween(@RequestParam("id") String userId, @RequestParam("start") String startDate, @RequestParam("end") String endDate, @RequestParam(value="category", required = false) MealPlan.MealCategory category) {
        LocalDateTime start = LocalDate.parse(startDate).atTime(0,0,0);// LocalDateTime.parse(startDate).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDate.parse(startDate).atTime(23,59,59);// LocalDateTime.parse(endDate).withHour(23).withMinute(59).withSecond(59);
        if (category == null)
            return mealPlanService.getMealPlansBetween(userId, start, end);
        return mealPlanService.getMealPlansBetweenAndCategory(userId, start, end, category);
    }

    @GetMapping("/summary/day")
    public DailySummaryVo getDailySummary(@RequestParam("id") String userId, @RequestParam("date") String createDate) {
        LocalDate date = LocalDate.parse(createDate);
        return mealPlanService.getDailySummary(userId, date);
    }

    @PutMapping("/plan")
    public void updateMealPlan(@RequestBody MealPlanDto dto) {
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
