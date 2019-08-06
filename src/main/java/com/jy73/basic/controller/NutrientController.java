package com.jy73.basic.controller;

import com.jy73.basic.service.NutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nutrient")
public class NutrientController {

    private final NutrientService nutrientService;

}
