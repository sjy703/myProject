package com.jy73.basic.service;

import com.jy73.basic.dto.MealPlanDto;
import com.jy73.basic.dto.NutrientDto;
import com.jy73.basic.entity.DailyBodyStatus;
import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.entity.Nutrient;
import com.jy73.basic.exception.CustomException;
import com.jy73.basic.exception.ErrorCode;
import com.jy73.basic.repository.Nutrient.NutrientRepository;
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
    private final NutrientRepository nutrientRepository;
    private final BodyStatusRepository bodyStatusRepository;

    @Transactional
    public void createMealPlan(MealPlan mealPlan) {
        mealPlanRepository.save(mealPlan);
    }


    public List<MealPlan> getMealPlansBetween(String userId, LocalDate start, LocalDate end) {
        return mealPlanRepository.findByUserIdAndCreateDateBetween(userId, start, end);
    }

    public List<MealPlan> getMealPlansBetweenAndCategory(String userId, LocalDate start, LocalDate end, MealPlan.MealCategory category) {
        return mealPlanRepository.findByUserIdAndCreateDateBetweenAndMealCategoryOrderByCreateTime(userId, start, end, category);
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
    public void updateRemark(MealPlanDto dto) {
        MealPlan mealPlan = getMealById(dto.getId());
        mealPlan.setRemark(dto.getRemark());
        mealPlanRepository.save(mealPlan);
    }

    @Transactional
    public void updateNutrient(MealPlanDto dto) {
        MealPlan mealPlan = getMealById(dto.getId());
        mealPlan.setNutrient(dto.getNutrient());
        saveAndDelete(dto, mealPlan);
    }

    @Transactional
    public void updateMealPlan(MealPlanDto dto) {
        MealPlan mealPlan = getMealById(dto.getId());
        mealPlan.setNutrient(dto.getNutrient());
        mealPlan.setRemark(dto.getRemark());
        mealPlan.setTotalCalories(dto.getTotalCalories());
        mealPlan.setTotalCarbohydrate(dto.getTotalCarbohydrate());
        mealPlan.setTotalFat(dto.getTotalFat());
        mealPlan.setTotalProtein(dto.getTotalProtein());
        mealPlan.setCreateTime(dto.getCreateTime());
        saveAndDelete(dto, mealPlan);
    }

    @Transactional
    private void saveAndDelete(MealPlanDto dto, MealPlan mealPlan) {
        mealPlanRepository.save(mealPlan);
        if (dto.getRemoveList() != null) {
            for (long id : dto.getRemoveList()) {
                nutrientRepository.deleteById(id);
            }
        }
    }

    @Transactional
    public void deleteMealPlan(long id) {
        mealPlanRepository.deleteById(id);
    }


    private MealPlan getMealById(long mealPlanId) {
        return mealPlanRepository.findById(mealPlanId).orElseThrow(() -> new CustomException(ErrorCode.NO_DATA, "식단이 없습니다."));
    }

    public DailySummaryVo getDailySummary(String userId, LocalDate date) {
        List<MealPlan> breakfast = getMealPlansBetweenAndCategory(userId, date, date, MealPlan.MealCategory.BREAKFAST);
        List<MealPlan> lunch = getMealPlansBetweenAndCategory(userId, date, date, MealPlan.MealCategory.LUNCH);
        List<MealPlan> dinner = getMealPlansBetweenAndCategory(userId, date, date, MealPlan.MealCategory.DINNER);
        List<MealPlan> nosh = getMealPlansBetweenAndCategory(userId, date, date, MealPlan.MealCategory.NOSH);


        List<DailyBodyStatus> dailyBodyStatus = new ArrayList<>();

        dailyBodyStatus.add(bodyStatusRepository.findByUserIdAndCreateDate(userId, date).orElse(null));


        DailySummaryVo summary = DailySummaryVo.builder().breakfast(breakfast.isEmpty() ? new MealPlan() : breakfast.get(0)).lunch(lunch.isEmpty() ? new MealPlan() : lunch.get(0))
                .dinner(dinner.isEmpty() ? new MealPlan() : dinner.get(0)).nosh(nosh)
                .dailyBodyStatus(dailyBodyStatus.get(0)).build();

        calculateTotalNutrient(summary);

        summary.setFatPercentage((summary.getTotalFat() * 9) / summary.getTotalCalories() * 100);
        summary.setCarbohydratePercentage((summary.getTotalCarbohydrate() * 9) / summary.getTotalCalories() * 100);
        summary.setProteinPercentage((summary.getTotalProtein() * 9) / summary.getTotalCalories() * 100);

        return summary;
    }

    private void calculateTotalNutrient(DailySummaryVo summary) {
        List<MealPlan> mealPlans = new ArrayList<>();
        mealPlans.add(summary.getBreakfast());
        mealPlans.add(summary.getLunch());
        mealPlans.add(summary.getDinner());
        mealPlans.addAll(summary.getNosh());
        for (MealPlan meal : mealPlans) {
                summary.setTotalCalories(summary.getTotalCalories() + meal.getTotalCalories());
                summary.setTotalFat(summary.getTotalFat() + meal.getTotalFat());
                summary.setTotalCarbohydrate(summary.getTotalCarbohydrate() + meal.getTotalCarbohydrate());
                summary.setTotalProtein(summary.getTotalProtein() + meal.getTotalProtein());
        }
    }
}
