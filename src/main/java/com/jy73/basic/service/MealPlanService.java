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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    @Transactional
    public void createMealPlan(MealPlan mealPlan) {
        mealPlanRepository.save(mealPlan);
    }

    public MealPlan getMealPlan(MealPlan mealPlan) {

        Optional<MealPlan> optionalMealPlan = mealPlanRepository.findByUserIdAndCreateDate(mealPlan.getUserId(), mealPlan.getCreateDate());
        MealPlan mealPlanResult = optionalMealPlan.orElseThrow(() -> new CustomException("식단이 없습니다."));
        return mealPlanResult;
    }

    @Transactional
    public void addNutrient(NutrientDto dto) {
        MealPlan mealPlan = getMealById(dto.getMealPlanId());
        Nutrient nutrient = Nutrient.builder().calorie(dto.getCalorie()).carbohydrate(dto.getCarbohydrate()).fat(dto.getFat()).protein(dto.getProtein()).foodName(dto.getFoodName()).build();
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
