package com.jy73.basic.repository.bodyStatus;

import com.jy73.basic.entity.DailyBodyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BodyStatusRepository extends JpaRepository<DailyBodyStatus, Long> {
    Optional<DailyBodyStatus> findByUserIdAndCreateDate(String userId, LocalDate date);
    List<DailyBodyStatus> findByUserIdAndCreateDateBetween(String userId, LocalDate start, LocalDate end);
}
