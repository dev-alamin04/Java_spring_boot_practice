package com.example.ecommerce.entity;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)

    private Long course_code;
    private String title;
    private String course_duration;
    private LocalTime class_to_time;
    private LocalTime class_from_time;
    private BigDecimal fee;
    private String teacher;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<StudentCourse>studentCourses;

}
