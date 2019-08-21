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
public class DailyBodyStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 30, nullable = false)
    private String userId;

    @ColumnDefault("0")
    private float weight;

    @ColumnDefault("0")
    private float ketone;

    @ColumnDefault("0")
    private float skeletalMuscleMass;

    @ColumnDefault("0")
    private float bodyFatPercentage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @PrePersist
    public void onPersist() {
        if(this.createDate == null){
            this.createDate = LocalDate.now();
        }
    }
}
