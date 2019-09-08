package com.jy73.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 30, nullable = false)
    private String userId;

    @Column(nullable = false)
    private MealCategory mealCategory;

    @Column(length = 500)
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime createTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Nutrient> nutrient = new ArrayList<>();


    @ColumnDefault("0")
    float totalCalories;

    @ColumnDefault("0")
    float totalFat;

    @ColumnDefault("0")
    float totalCarbohydrate;

    @ColumnDefault("0")
    float totalProtein;

    @PrePersist
    public void onPersist() {
        if (this.createDate == null) {
            this.createDate = LocalDate.now();
        }
    }

    public enum MealCategory {
        BREAKFAST("breakfast"),
        LUNCH("lunch"),
        DINNER("dinner"),
        NOSH("nosh");
        String category;

        MealCategory(String category) {
            this.category = category;
        }

        public String getCategory() {
            return this.category;
        }
    }
}