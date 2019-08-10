package com.jy73.basic.repository.Nutrient;

import com.jy73.basic.entity.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
    List<Nutrient> findByUserIdAndCreateDateBetween(String userId, LocalDate start, LocalDate end);
}
