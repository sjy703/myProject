package com.jy73.basic.controller;

import com.jy73.basic.entity.DailyBodyStatus;
import com.jy73.basic.service.BodyStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/body")
public class BodyStatusController {

    private final BodyStatusService bodyStatusService;

    @PostMapping
    public void createBodyStatus(@RequestBody DailyBodyStatus dailyBodyStatus) {
        bodyStatusService.createBodyStatus(dailyBodyStatus);
    }

    @GetMapping
    public DailyBodyStatus getBodyStatus(@RequestParam("id") String userId, @RequestParam("date") String createDate) {
        LocalDate date = LocalDate.parse(createDate);
        return bodyStatusService.getBodyStatus(userId, date);
    }

    @GetMapping("/duration")
    public List<DailyBodyStatus> getBodyStatus(@RequestParam("id") String userId, @RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return bodyStatusService.getBodyStatusList(userId, start, end);
    }

    @PutMapping
    public void updateBodyStatus(@RequestBody DailyBodyStatus dailyBodyStatus) {
         bodyStatusService.updateBodyStatus(dailyBodyStatus);
    }
}
