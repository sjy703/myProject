package com.jy73.basic.service;

import com.jy73.basic.dto.MealPlanDto;
import com.jy73.basic.dto.NutrientDto;
import com.jy73.basic.entity.MealPlan;
import com.jy73.basic.entity.Nutrient;
import com.jy73.basic.exception.CustomException;
import com.jy73.basic.repository.mealPlan.MealPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    @Transactional
    public void createMealPlan(MealPlan mealPlan) {
        mealPlanRepository.save(mealPlan);
    }

    public List<MealPlan> getMealPlans(MealPlan mealPlan) {
        List<MealPlan> optionalMealPlan = mealPlanRepository.findByUserIdAndCreateDate(mealPlan.getUserId(), mealPlan.getCreateDate());
        if (optionalMealPlan.isEmpty()) {
            throw new CustomException("식단이 없습니다.");
        }
        return optionalMealPlan;
    }

    public List<MealPlan> getMealPlansBetween(String userId, LocalDate start,  LocalDate end) {
        List<MealPlan> mealPlans = mealPlanRepository.findByUserIdAndCreateDateBetween(userId, start, end);
        if (mealPlans.isEmpty()) {
            throw new CustomException("영양 정보가 없습니다.");
        }
        return mealPlans;
    }

    @Transactional
    public void addNutrient(NutrientDto dto) {
        MealPlan mealPlan = getMealById(dto.getMealPlanId());
        Nutrient nutrient = Nutrient.builder().calorie(dto.getCalorie()).carbohydrate(dto.getCarbohydrate()).
                fat(dto.getFat()).protein(dto.getProtein()).foodName(dto.getFoodName()).
                createDate(dto.getCreateDate()).intake(dto.getIntake()).unit(dto.getUnit()).userId(dto.getUserId()).build();
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
        mealPlan.setTitle(dto.getTitle());
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

}
