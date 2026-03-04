package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
}
