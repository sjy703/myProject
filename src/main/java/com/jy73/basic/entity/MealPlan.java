package com.jy73.basic.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "parent_id")
            List<Nutrient> nutrient = new ArrayList<>();

    @PrePersist
    public void onPersist() {
        if(this.createDate == null){
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

        public String getCategory()
        {
            return this.category;
        }
    }
}