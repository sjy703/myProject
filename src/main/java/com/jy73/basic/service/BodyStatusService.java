package com.jy73.basic.service;


import com.jy73.basic.entity.DailyBodyStatus;
import com.jy73.basic.exception.CustomException;
import com.jy73.basic.repository.bodyStatus.BodyStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BodyStatusService {

    private final BodyStatusRepository bodyStatusRepository;

    @Transactional
    public void createBodyStatus(DailyBodyStatus dailyBodyStatus) {
        bodyStatusRepository.save(dailyBodyStatus);
    }

    public DailyBodyStatus getBodyStatus(String userId, LocalDate createDate) {
        Optional<DailyBodyStatus> optionalDailyBodyStatus = bodyStatusRepository.findByUserIdAndCreateDate(userId, createDate);
        DailyBodyStatus dailyBodyStatus = optionalDailyBodyStatus.orElseThrow(() -> new CustomException("결과가 없습니다."));
        return dailyBodyStatus;
    }

    public List<DailyBodyStatus> getBodyStatusList(String userId, LocalDate start, LocalDate end) {
        List<DailyBodyStatus> dailyBodyStatus = bodyStatusRepository.findByUserIdAndCreateDateBetween(userId, start, end);
        /*if (dailyBodyStatus.isEmpty()) {
            throw new CustomException("결과가 없습니다.");
        }*/
        return dailyBodyStatus;
    }

    @Transactional
    public void updateBodyStatus(DailyBodyStatus dailyBodyStatus) {
        DailyBodyStatus result = getBodyStatus(dailyBodyStatus.getUserId(), dailyBodyStatus.getCreateDate());
        result.setBodyFatPercentage(dailyBodyStatus.getBodyFatPercentage());
        result.setKetone(dailyBodyStatus.getKetone());
        result.setSkeletalMuscleMass(dailyBodyStatus.getSkeletalMuscleMass());
        result.setWeight(dailyBodyStatus.getWeight());
        bodyStatusRepository.save(result);
    }

}
