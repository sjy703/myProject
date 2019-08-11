package com.jy73.basic.service;

import com.jy73.basic.controller.MealPlanController;
import com.jy73.basic.dto.MealPlanDto;
import com.jy73.basic.dto.NutrientDto;
import com.jy73.basic.entity.DailyBodyStatus;
import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.entity.Nutrient;
import com.jy73.basic.exception.CustomException;
import com.jy73.basic.repository.bodyStatus.BodyStatusRepository;
import com.jy73.basic.repository.mealPlan.MealPlanRepository;
import com.jy73.basic.vo.DailySummaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    private final BodyStatusRepository bodyStatusRepository;

    @Transactional
    public void createMealPlan(MealPlan mealPlan) {
        mealPlanRepository.save(mealPlan);
    }

    public List<MealPlan> getMealPlans(String userId, LocalDate createDate) {
        List<MealPlan> mealPlan = mealPlanRepository.findByUserIdAndCreateDate(userId, createDate);
        return mealPlan;
    }

    public List<MealPlan> getMealPlansByCategory(String userId, LocalDate createDate, MealPlan.MealCategory category) {
        List<MealPlan> mealPlan = mealPlanRepository.findByUserIdAndCreateDateAndMealCategory(userId, createDate, category);
        return mealPlan;
    }

    public List<MealPlan> getMealPlansBetween(String userId, LocalDate start, LocalDate end) {
        List<MealPlan> mealPlans = mealPlanRepository.findByUserIdAndCreateDateBetween(userId, start, end);
        /*if (mealPlans.isEmpty()) {
            throw new CustomException("영양 정보가 없습니다.");
        }*/
        return mealPlans;
    }

    public List<MealPlan> getMealPlansBetweenAndCategory(String userId, LocalDate start, LocalDate end, MealPlan.MealCategory category) {
        List<MealPlan> mealPlans = mealPlanRepository.findByUserIdAndCreateDateBetweenAndMealCategory(userId, start, end, category);
        /*if (mealPlans.isEmpty()) {
            throw new CustomException("영양 정보가 없습니다.");
        }*/
        return mealPlans;
    }

    @Transactional
    public void addNutrient(NutrientDto dto) {
        MealPlan mealPlan = getMealById(dto.getMealPlanId());
        Nutrient nutrient = Nutrient.builder().calorie(dto.getCalorie()).carbohydrate(dto.getCarbohydrate()).
                fat(dto.getFat()).protein(dto.getProtein()).foodName(dto.getFoodName()).
                createDate(dto.getCreateDate()).intake(dto.getIntake()).unit(dto.getUnit()).userId(dto.getUserId()).mealCategory(mealPlan.getMealCategory()).build();
        mealPlan.getNutrient().add(nutrient);
        mealPlanRepository.save(mealPlan);
    }

    @Transactional
    public void removeNutrient(long mealPlanId, long id) {
        MealPlan mealPlan = getMealById(mealPlanId);
        mealPlan.getNutrient().removeIf(nutrient -> nutrient.getId() == id);
        mealPlanRepository.save(mealPlan);
    }

    @Transactional
    public void updateMealPlan(MealPlanDto dto) {
        MealPlan mealPlan = getMealById(dto.getId());
        mealPlan.setRemark(dto.getRemark());
        mealPlanRepository.save(mealPlan);
    }

    @Transactional
    public void deleteMealPlan(long id) {
        mealPlanRepository.deleteById(id);
    }


    public MealPlan getMealById(long mealPlanId) {
        return mealPlanRepository.findById(mealPlanId).orElseThrow(() -> new CustomException("식단이 없습니다."));
    }

    public DailySummaryVo getDailySummary(String userId, LocalDate date) {
        List<MealPlan> breakFast = getMealPlansByCategory(userId, date, MealPlan.MealCategory.BREAKFAST);
        List<MealPlan> lunch = getMealPlansByCategory(userId, date, MealPlan.MealCategory.LUNCH);
        List<MealPlan> dinner = getMealPlansByCategory(userId, date, MealPlan.MealCategory.DINNER);
        List<MealPlan> nosh = getMealPlansByCategory(userId, date, MealPlan.MealCategory.NOSH);


        List<DailyBodyStatus> dailyBodyStatus = new ArrayList<>();
        dailyBodyStatus.add(bodyStatusRepository.findByUserIdAndCreateDate(userId, date).orElse(null));

        DailySummaryVo summary = DailySummaryVo.builder().breakfast(breakFast).lunch(lunch).dinner(dinner).nosh(nosh)
                .dailyBodyStatus(dailyBodyStatus).build();

        calculateTotalNutrient(breakFast, summary);
        calculateTotalNutrient(lunch, summary);
        calculateTotalNutrient(dinner, summary);
        calculateTotalNutrient(nosh, summary);

        return summary;
    }

    private void calculateTotalNutrient(List<MealPlan> mealPlans, DailySummaryVo summary) {
        float totalCalories = summary.getTotalCalories();
        float totalFat = summary.getTotalFat();
        float totalCarbohydrate = summary.getTotalCarbohydrate();
        float totalProtein = summary.getTotalProtein();

        for (MealPlan meal : mealPlans) {
            for (Nutrient nutrient : meal.getNutrient()) {
                summary.setTotalCalories(totalCalories + nutrient.getCalorie());
                summary.setTotalFat(totalFat + nutrient.getFat());
                summary.setTotalCarbohydrate(totalCarbohydrate + nutrient.getCarbohydrate());
                summary.setTotalProtein(totalProtein + nutrient.getProtein());
            }
        }
    }
}
