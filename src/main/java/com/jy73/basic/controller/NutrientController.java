package com.jy73.basic.controller;

import com.jy73.basic.entity.Nutrient;
import com.jy73.basic.service.NutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nutrient")
public class NutrientController {

    private final NutrientService nutrientService;

    @GetMapping
    public Nutrient getNurient(long id) {
       return nutrientService.getNutrient(id);
    }

    @GetMapping("/list")
    public List<Nutrient> getNutrients(@RequestParam("id") String userId,@RequestParam("start") String startDate, @RequestParam("end")String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return nutrientService.getNutrients(userId, start, end);
    }
}
