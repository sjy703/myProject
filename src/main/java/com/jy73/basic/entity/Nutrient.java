package com.jy73.basic.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String foodName;

    @ColumnDefault("0")
    private float calorie;

    @ColumnDefault("0")
    private float carbohydrate;

    @ColumnDefault("0")
    private float protein;

    @ColumnDefault("0")
    private float fat;

    @ColumnDefault("0")
    private int intake;

    private Unit unit;

    @Column(nullable = false)
    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @Column(nullable = false)
    private MealPlan.MealCategory mealCategory;

    @PrePersist
    public void onPersist() {
        if (this.createDate == null) {
            this.createDate = LocalDate.now();
        }
    }

    public enum Unit {
        G("g"), SERVING("인분");
        String unit;

        Unit(String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return this.unit;
        }
    }
}
