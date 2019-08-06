package com.jy73.basic.service;

import com.jy73.basic.repository.Nutrient.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NutrientService {

    private final NutrientRepository nutrientRepository;

}
