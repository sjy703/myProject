package com.jy73.basic.service;

import com.jy73.basic.entity.Nutrient;
import com.jy73.basic.exception.CustomException;
import com.jy73.basic.exception.ErrorCode;
import com.jy73.basic.repository.Nutrient.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NutrientService {

    private final NutrientRepository nutrientRepository;

    public Nutrient getNutrient(long id) {
        Optional<Nutrient> nutrientOptioal = nutrientRepository.findById(id);
        Nutrient nutrient = nutrientOptioal.orElseThrow(() -> new CustomException(ErrorCode.NO_DATA, "영양 정보가 없습니다."));
        return nutrient;
    }

    public List<Nutrient> getNutrients(String userId, LocalDate start, LocalDate end) {
        List<Nutrient> nutrients = nutrientRepository.findByUserIdAndCreateDateBetween(userId, start, end);
        if (nutrients.isEmpty()) {
            throw new CustomException(ErrorCode.NO_DATA, "영양 정보가 없습니다.");
        }
        return nutrients;
    }

}
