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

    private float calorie;

    private float carbohydrate;

    private float protein;

    private float fat;

    @ColumnDefault("0")
    private int intake;

    private Unit unit;

    @Column(nullable = false)
    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    /*@ManyToOne
    private MealPlan mealPlan;*/

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
