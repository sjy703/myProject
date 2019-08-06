package com.jy73.basic.entity;

import lombok.*;

import javax.persistence.*;

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

    /*@ManyToOne
    private MealPlan mealPlan;*/
}
